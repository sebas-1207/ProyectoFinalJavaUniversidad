package com.campusland.respository.impl.impldepartamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.respository.RepositoryDepartamento;
import com.campusland.respository.models.Departamentos;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryDepartamentoMysqlImpl implements RepositoryDepartamento {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Departamentos> listar() {
        List<Departamentos> listDepartamentos = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Departamentos")) {
            while (rs.next()) {
                listDepartamentos.add(crearDepartamento(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDepartamentos;
    }

    @Override
    public Departamentos porNombre(String nombreDepartamento) {
        Departamentos departamentos = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT * FROM Departamentos WHERE nombreDepartamento=?")) {
            stmt.setString(1, nombreDepartamento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    departamentos = crearDepartamento(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departamentos;
    }

    @Override
    public void crear(Departamentos departamentos) {
        String sql = "INSERT INTO Departamentos(nombreDepartamento) VALUES(?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, departamentos.getNombreDepartamento());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Departamentos departamentos) {
        String sql = "UPDATE Departamentos SET nombreDepartamento=? WHERE idDepartamento=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, departamentos.getNombreDepartamento());
            stmt.setInt(2, departamentos.getIdDepartamento());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Departamentos departamentos) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Departamentos WHERE idDepartamento=?")) {
            stmt.setInt(1, departamentos.getIdDepartamento());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Departamentos crearDepartamento(ResultSet rs) throws SQLException {
        Departamentos departamentos = new Departamentos();
        departamentos.setIdDepartamento(rs.getInt("idDepartamento"));
        departamentos.setNombreDepartamento(rs.getString("nombreDepartamento"));
        return departamentos;
    }

}
