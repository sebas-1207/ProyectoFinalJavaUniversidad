package com.campusland.repository.impl.implalumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.campusland.repository.RepositoryAlumno;
import com.campusland.repository.impl.implpersona.RepositoryPersonasMysqlImpl;
import com.campusland.repository.models.Alumnos;
import com.campusland.repository.models.Personas;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryAlumnoMysqlImpl implements RepositoryAlumno {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Personas> listar() {
        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        return repositoryPersonasMysqlImpl.listarAlumnos();
    }

    @Override
    public Personas porDocumento(String numeroDocumento) {
        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        return repositoryPersonasMysqlImpl.porDocumento(numeroDocumento);
    }

    @Override
    public void crear(Alumnos alumnos) {
        String sql = "INSERT INTO Alumnos (personaId, programaId) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, alumnos.getPersonaId());
            stmt.setInt(2, alumnos.getProgramaId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crear(Personas personas, int programId) {

        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        repositoryPersonasMysqlImpl.crear(personas);

        Personas personaCreada = repositoryPersonasMysqlImpl.porDocumento(personas.getNumeroDocumento());

        Alumnos alumnos = new Alumnos(personaCreada.getIdPersona(), programId);

        crear(alumnos);
    }

    @Override
    public void editar(Alumnos alumnos) {
        String sql = "UPDATE Alumnos SET programaId WHERE personaId = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, alumnos.getPersonaId());
            stmt.setInt(2, alumnos.getProgramaId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Alumnos alumnos) {
        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        String sql = "DELETE FROM Alumnos WHERE idAlumno = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, alumnos.getIdAlumno());
            stmt.executeUpdate();
            repositoryPersonasMysqlImpl.eliminar(alumnos.getPersonaId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void eliminar(int personaId) {
        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        String sql = "DELETE FROM Alumnos WHERE personaId = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, personaId);
            stmt.executeUpdate();
            repositoryPersonasMysqlImpl.eliminar(personaId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
