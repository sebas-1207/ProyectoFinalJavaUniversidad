package com.campusland.repository.impl.implprograma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.repository.RepositoryPrograma;
import com.campusland.repository.models.Programas;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryProgramaMysqlImpl implements RepositoryPrograma {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Programas> listar() {
        List<Programas> listProgramas = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Programas")) {
            while (rs.next()) {
                listProgramas.add(crearPrograma(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProgramas;
    }

    @Override
    public Programas porNombre(String nombrePrograma) {
        Programas programas = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Programas WHERE nombrePrograma=?")) {
            stmt.setString(1, nombrePrograma);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    programas = crearPrograma(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programas;
    }

    @Override
    public void crear(Programas programas) {
        String sql = "INSERT INTO Programas(nombrePrograma, nivel) VALUES(?,?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, programas.getNombrePrograma());
            stmt.setString(2, programas.getNivel());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Programas programas) {
        String sql = "UPDATE Programas SET nombrePrograma = ?, nivel = ? WHERE idPrograma = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, programas.getNombrePrograma());
            stmt.setString(2, programas.getNivel());
            stmt.setInt(3, programas.getIdPrograma()); 
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Programas programas) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Programas WHERE idPrograma=?")) {
            stmt.setInt(1, programas.getIdPrograma());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Programas crearPrograma(ResultSet rs) throws SQLException {
        Programas programas = new Programas();
        programas.setIdPrograma(rs.getInt("idPrograma"));
        programas.setNombrePrograma(rs.getString("nombrePrograma"));
        programas.setNivel(rs.getString("nivel"));
        return programas;
    }

}
