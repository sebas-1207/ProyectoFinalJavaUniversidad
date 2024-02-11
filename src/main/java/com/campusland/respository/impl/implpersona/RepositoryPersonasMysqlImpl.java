package com.campusland.respository.impl.implpersona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.respository.RepositoryPersonas;
import com.campusland.respository.models.Ciudad;
import com.campusland.respository.models.Direccion;
import com.campusland.respository.models.Personas;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryPersonasMysqlImpl implements RepositoryPersonas {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Personas> listar() {
        List<Personas> listPersonas = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Personas")) {
            while (rs.next()) {
                listPersonas.add(crearPersona(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPersonas;
    }

    @Override
    public Personas porDocumento(String numeroDocumento) {
        Personas personas = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Personas WHERE numeroDocumento=?")) {
            stmt.setString(1, numeroDocumento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    personas = crearPersona(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }


    @Override
    public void crear(Personas personas) {
        try (PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO Personas (tipoDocumento, numeroDocumento, nombre, apellido, numeroTelefono, fechaNacimiento, sexo, direccionNumero, ciudadID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");) {
            stmt.setString(1, personas.getTipoDocumento());
            stmt.setString(2, personas.getNumeroDocumento());
            stmt.setString(3, personas.getNombre());
            stmt.setString(4, personas.getApellido());
            stmt.setString(5, personas.getNumeroTelefono());
            stmt.setDate(6, personas.getFechaNacimiento());
            stmt.setString(7, personas.getSexo());
            stmt.setString(8, personas.getDireccionNumero());
            stmt.setInt(9, personas.getCiudadId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void editar(Personas personas) {
        String sql = "UPDATE Personas SET tipoDocumento=?, numeroDocumento=?, nombre=?, apellido=?, numeroTelefono=?, fechaNacimiento=?, sexo=?, direccionNumero=?, ciudadID=? WHERE idPersona=?";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personas.getTipoDocumento());
            stmt.setString(2, personas.getNumeroDocumento());
            stmt.setString(3, personas.getNombre());
            stmt.setString(4, personas.getApellido());
            stmt.setString(5, personas.getNumeroTelefono());
            stmt.setDate(6, personas.getFechaNacimiento());
            stmt.setString(7, personas.getSexo());
            stmt.setInt(10, personas.getIdPersona());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Personas personas) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Personas WHERE idPersona=?")) {
            stmt.setInt(1, personas.getIdPersona());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Personas crearPersona(ResultSet rs) throws SQLException {
        Personas persona = new Personas();
        persona.setIdPersona(rs.getInt("idPersona"));
        persona.setTipoDocumento(rs.getString("tipoDocumento"));
        persona.setNumeroDocumento(rs.getString("numeroDocumento"));
        persona.setNombre(rs.getString("nombre"));
        persona.setApellido(rs.getString("apellido"));
        persona.setNumeroTelefono(rs.getString("numeroTelefono"));
        persona.setFechaNacimiento(rs.getDate("fechaNacimiento"));
        persona.setSexo(rs.getString("sexo"));
        Direccion direccion = new Direccion();
        direccion.setNumeroDireccion(rs.getInt("direccionNumero"));
        Ciudad ciudad = new Ciudad();
        ciudad.setIdCiudad(rs.getInt("ciudadID"));

        return persona;
    }
}
