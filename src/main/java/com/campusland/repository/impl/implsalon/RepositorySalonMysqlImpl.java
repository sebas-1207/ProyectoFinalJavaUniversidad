package com.campusland.repository.impl.implsalon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.repository.RepositorySalon;
import com.campusland.repository.models.Salones;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositorySalonMysqlImpl implements RepositorySalon {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Salones> listar() {
        List<Salones> listSalones = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Salones")) {
            while (rs.next()) {
                listSalones.add(crearSalones(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSalones;
    }

    @Override
    public Salones porNumero(int numeroIdentificacion) {
        Salones salones = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Salones WHERE numeroIdentificacion =?")) {
            stmt.setInt(1, numeroIdentificacion);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    salones = crearSalones(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salones;
    }

    @Override
    public void crear(Salones salones) {
        String sql = "INSERT INTO Salones(capacidadAlumnos, numeroIdentificacion, edificioId) VALUES (?,?,?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salones.getCapacidadAlumnos());
            stmt.setInt(2, salones.getNumeroIdentificacion());
            stmt.setInt(3, salones.getEdificioId());
            stmt.executeUpdate();
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Salones salones) {
        String sql = "UPDATE Salones SET capacidadAlumnos = ?, numeroIdentificacion = ?, edificioId = ? WHERE idSalon = ?";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salones.getCapacidadAlumnos());
            stmt.setInt(2, salones.getNumeroIdentificacion());
            stmt.setInt(3, salones.getEdificioId());
            stmt.setInt(4, salones.getIdSalones());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Salones salones) {
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Salones WHERE idSalon = ?")) {
                stmt.setInt(1, salones.getIdSalones());
                stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Salones crearSalones(ResultSet rs) throws SQLException {
        Salones salones = new Salones();
        salones.setIdSalones(rs.getInt("idSalones"));
        salones.setCapacidadAlumnos(rs.getInt("capacidadAlumnos"));
        salones.setNumeroIdentificacion(rs.getInt("numeroIdentificacion"));
        salones.setEdificioId(rs.getInt("edificioId"));
        return salones;
    }

}
