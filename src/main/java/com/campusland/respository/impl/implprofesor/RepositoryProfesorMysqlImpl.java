package com.campusland.respository.impl.implprofesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.respository.RepositoryProfesor;
import com.campusland.respository.impl.implpersona.RepositoryPersonasMysqlImpl;
import com.campusland.respository.models.Personas;
import com.campusland.respository.models.Profesores;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryProfesorMysqlImpl implements RepositoryProfesor {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Profesores> listar() {
        List<Profesores> listProfesores = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Profesores")) {
            while (rs.next()) {
                listProfesores.add(crearProfesor(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProfesores;
    }

    @Override
    public Profesores porDocumento(String numeroDocumento) {
        Profesores profesores = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT * FROM Profesores p INNER JOIN Personas pe ON p.personaId = pe.idPersona WHERE pe.numeroDocumento = ?")) {
            stmt.setString(1, numeroDocumento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    profesores = crearProfesor(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesores;
    }

    @Override
    public void crear(Profesores profesores) {

        String sql = "INSERT INTO Profesores (profesorId, especialidad, personaId, departamentoId) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, profesores.getIdProfesor());
            stmt.setString(2, profesores.getEspecialidad());
            stmt.setInt(3, profesores.getPersonaId());
            stmt.setInt(4, profesores.getDepartamentoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crear(String especialidad, Personas personas, int departamentoId) {

        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        repositoryPersonasMysqlImpl.crear(personas);

        Personas personaCreada = repositoryPersonasMysqlImpl.porCodigo(personas.getIdPersona());

        Profesores profesores = new Profesores(especialidad, personaCreada.getIdPersona(), departamentoId);

        crear(profesores);

    }

    @Override
    public void editar(Profesores profesores) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE Profesores SET especialidad = ?, personaId = ? WHERE idProfesor = ?")) {
            stmt.setString(1, profesores.getEspecialidad());
            stmt.setInt(2, profesores.getPersonaId());
            stmt.setInt(3, profesores.getIdProfesor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Profesores profesores) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Profesores WHERE idProfesor = ?")) {
            stmt.setInt(1, profesores.getIdProfesor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Profesores crearProfesor(ResultSet rs) throws SQLException {
        Profesores profesores = new Profesores();
        profesores.setIdProfesor(rs.getInt("idProfesor"));
        profesores.setEspecialidad(rs.getString("especialidad"));
        profesores.setPersonaId(rs.getInt("personaId"));
        return profesores;
    }

}
