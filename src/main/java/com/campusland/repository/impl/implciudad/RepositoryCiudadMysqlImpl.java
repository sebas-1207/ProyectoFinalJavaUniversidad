package com.campusland.repository.impl.implciudad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.repository.RepositoryCiudad;
import com.campusland.repository.models.Ciudad;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryCiudadMysqlImpl implements RepositoryCiudad {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Ciudad> listar() {
        List<Ciudad> lisCiudad = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Ciudad")) {
            while (rs.next()) {
                lisCiudad.add(crearCiudad(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lisCiudad;
    }

    @Override
    public Ciudad porNombre(String nombreCiudad) {
        Ciudad ciudad = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Ciudad WHERE nombreCiudad = ?")) {
            stmt.setString(1, nombreCiudad);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ciudad = crearCiudad(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ciudad;
    }

    @Override
    public void crear(Ciudad ciudad) {
        String sql = "INSERT INTO Ciudad(nombreCiudad) VALUES (?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ciudad.getNombreCiudad());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Ciudad ciudad) {
        String sql = "UPDATE Ciudad SET nombreCiudad = ? WHERE idCiudad = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ciudad.getNombreCiudad());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Ciudad crearCiudad(ResultSet rs) throws SQLException {
        Ciudad ciudad = new Ciudad();
        ciudad.setIdCiudad(rs.getInt("idCiudad"));
        ciudad.setNombreCiudad(rs.getString("nombreCiudad"));
        return ciudad;
    }

}
