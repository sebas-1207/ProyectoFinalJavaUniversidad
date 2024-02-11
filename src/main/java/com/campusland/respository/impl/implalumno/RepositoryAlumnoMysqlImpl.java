package com.campusland.respository.impl.implalumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.respository.RepositoryAlumno;
import com.campusland.respository.impl.implpersona.RepositoryPersonasMysqlImpl;
import com.campusland.respository.models.Alumnos;
import com.campusland.respository.models.Personas;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryAlumnoMysqlImpl implements RepositoryAlumno {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Alumnos> listar() {
        List<Alumnos> listAlumnos = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Alumnos")) {
            while (rs.next()) {
                listAlumnos.add(crearAlumno(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAlumnos;
    }

    @Override
    public Alumnos porDocumento(String numeroDocumento) {
        Alumnos alumnos = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Alumnos WHERE numeroDocumento=?")) {
            stmt.setString(1, numeroDocumento);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    alumnos = crearAlumno(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public void crear(Alumnos alumnos) {
        String sql = "INSERT INTO Alumnos (idAlumno, personaId) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, alumnos.getIdAlumno());
            stmt.setInt(2, alumnos.getPersonaId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }     
    }

    public void crear(Personas personas) {

        RepositoryPersonasMysqlImpl repositoryPersonasMysqlImpl = new RepositoryPersonasMysqlImpl();
        repositoryPersonasMysqlImpl.crear(personas);

        Personas personaCreada = repositoryPersonasMysqlImpl.porDocumento(personas.getNumeroDocumento());
        Alumnos alumnos = new Alumnos(personaCreada.getIdPersona());

        crear(alumnos);
    }

    @Override
    public void editar(Alumnos alumnos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    @Override
    public void eliminar(Alumnos alumnos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    private Alumnos crearAlumno(ResultSet rs) throws SQLException{  
        Alumnos alumnos = new Alumnos();
        alumnos.setIdAlumno(rs.getInt("idAlumno"));
        alumnos.setPersonaId(rs.getInt("personaId"));
        return alumnos;
    }

}
