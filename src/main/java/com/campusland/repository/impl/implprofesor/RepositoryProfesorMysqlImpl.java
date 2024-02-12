package com.campusland.repository.impl.implprofesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.repository.RepositoryProfesor;
import com.campusland.repository.impl.implpersona.RepositoryPersonasMysqlImpl;
import com.campusland.repository.models.Alumnos;
import com.campusland.repository.models.Personas;
import com.campusland.repository.models.Profesores;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryProfesorMysqlImpl implements RepositoryProfesor {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Personas> listar() {
        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        return repositoryPersonasMysqlImpl.listarProfesores();
    }

    @Override
    public Personas porDocumento(String numeroDocumento) {
        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        return repositoryPersonasMysqlImpl.porDocumento(numeroDocumento);
    }

    @Override
    public void crear(Profesores profesores) {
        String sql = "INSERT INTO Profesores (especialidad, personaId, departamentoId) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, profesores.getEspecialidad());
            stmt.setInt(2, profesores.getPersonaId());
            stmt.setInt(3, profesores.getDepartamentoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crear(String especialidad, Personas personas, int departamentoId) {

        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        repositoryPersonasMysqlImpl.crear(personas);

        Personas personaCreada = repositoryPersonasMysqlImpl.porDocumento(personas.getNumeroDocumento());

        Profesores profesores = new Profesores(especialidad, personaCreada.getIdPersona(), departamentoId);

        crear(profesores);
    }

    @Override
    public void editar(Profesores profesores) {
        String sql = "UPDATE Profesores SET departamentoId WHERE personaId = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, profesores.getEspecialidad());
            stmt.setInt(2, profesores.getPersonaId());
            stmt.setInt(3, profesores.getDepartamentoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Profesores profesores) {
        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        String sql = "DELETE FROM Profesores WHERE personaId = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, profesores.getIdProfesor());
            stmt.executeUpdate();
            repositoryPersonasMysqlImpl.eliminar(profesores.getPersonaId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void eliminar(int personaId) {
        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        String sql = "DELETE FROM Profesores WHERE personaId = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, personaId);
            stmt.executeUpdate();
            repositoryPersonasMysqlImpl.eliminar(personaId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
