package com.campusland.repository.impl.impldireccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.exceptiones.direccionexceptions.DireccionNullException;
import com.campusland.repository.RepositoryDireccion;
import com.campusland.repository.enums.TipoDireccion;
import com.campusland.repository.models.Direccion;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryDireccionImpl implements RepositoryDireccion {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Direccion> listar() {
        List<Direccion> listDireccion = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Direccion")) {
            while (rs.next()) {
                listDireccion.add(crearDireccion(rs));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDireccion;
    }

    @Override
    public void crear(Direccion direccion) throws DireccionNullException {
        String sql = "INSERT INTO Direccion(tipoDireccion, numero, barrio) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            String tipoDireccionStr = direccion.getTipoDireccion().name();
            stmt.setString(1, tipoDireccionStr);
            stmt.setInt(2, direccion.getNumero());
            stmt.setString(3, direccion.getBarrio());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Direccion crearDireccion(ResultSet rs) throws SQLException {
        Direccion direccion = new Direccion();
        direccion.setNumeroDireccion(rs.getInt("numeroDireccion"));
        String tipoDireccionStr = rs.getString("tipoDireccion"); // Obtener el String desde el ResultSet
        // Convertir el String a Enum
        TipoDireccion tipoDireccionEnum = TipoDireccion.valueOf(tipoDireccionStr);
        // Luego, establece el Enum en el objeto de Direccion
        direccion.setTipoDireccion(tipoDireccionEnum);
        direccion.setNumero(rs.getInt("numero"));
        direccion.setBarrio(rs.getString("barrio"));
        return direccion;
    }

}
