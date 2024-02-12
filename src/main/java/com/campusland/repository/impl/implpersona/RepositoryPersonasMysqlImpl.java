package com.campusland.repository.impl.implpersona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.repository.RepositoryPersonas;
import com.campusland.repository.models.Personas;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryPersonasMysqlImpl implements RepositoryPersonas {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Personas> listar() {
        List<Personas> listPersonas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Personas");) {
            while (rs.next()) {
                listPersonas.add(crearPersona(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPersonas;
    }

    public List<Personas> listarAlumnos() {
        List<Personas> listPersonas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt
                        .executeQuery("SELECT * FROM Personas p INNER JOIN Alumnos a ON p.idPersona = s.personaId");) {
            while (rs.next()) {
                listPersonas.add(crearPersona(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPersonas;
    }

    public List<Personas> listarProfesores(){
        List<Personas> listPersonas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Personas p INNER JOIN Profesores p ON p.idPersona = p.personaId");) {
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
        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Personas WHERE numeroDocumento = ?");) {
            stmt.setString(1, numeroDocumento);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()){
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
        try (PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO Personas (tipoDocumento, numeroDocumento, nombre, apellido, numeroTelefono, fechaNacimiento, sexo, direccionNumero, ciudadID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");) {
            stmt.setString(1, personas.getTipoDocumento());
            stmt.setString(2, personas.getNumeroDocumento());
            stmt.setString(3, personas.getNombre());
            stmt.setString(4, personas.getApellido());
            stmt.setString(5, personas.getNumeroTelefono());
            stmt.setDate(6, personas.getFechaNacimiento());
            stmt.setString(7, personas.getSexo());
            stmt.setInt(8, personas.getDireccionNumero());
            stmt.setInt(9, personas.getCiudadId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Personas personas) {
        try (PreparedStatement stmt = getConnection().prepareStatement("UPDATE Persona SET tipoDocumento = ?, numeroDocumento = ?, nombre = ?, apellido = ?, numeroTelefono = ?, fechaNacimiento = ?, sexo = ?, direccionNumero = ?, ciudadId = ? WHERE numeroDocumento = ?");) {
            stmt.setString(1, personas.getTipoDocumento());
            stmt.setString(2, personas.getNumeroDocumento());
            stmt.setString(3, personas.getNombre());
            stmt.setString(4, personas.getApellido());
            stmt.setString(5, personas.getNumeroTelefono());
            stmt.setDate(6, personas.getFechaNacimiento());
            stmt.setString(7, personas.getSexo());
            stmt.setInt(8, personas.getDireccionNumero());
            stmt.setInt(9, personas.getCiudadId());
            stmt.setString(10, personas.getNumeroDocumento());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Personas personas) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM Personas WHERE numeroDocumento = ?");) {
            stmt.setString(1, personas.getNumeroDocumento());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void eliminar(int idPersona){
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM Personas WHERE idPersona = ? ");) {
            stmt.setInt(1, idPersona);        
            stmt.executeUpdate();    
        } catch (SQLException e) {
            e.printStackTrace();
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
        persona.setDireccionNumero(rs.getInt("direccionNumero"));
        persona.setCiudadId(rs.getInt("ciudadId"));
        return persona;
    }
}
