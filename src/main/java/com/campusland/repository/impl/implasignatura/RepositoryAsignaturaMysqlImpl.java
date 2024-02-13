package com.campusland.repository.impl.implasignatura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.repository.RepositoryAsignaturas;
import com.campusland.repository.models.Asignaturas;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryAsignaturaMysqlImpl implements RepositoryAsignaturas {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Asignaturas> listar() {
        List<Asignaturas> listAsignaturas = new ArrayList<>();
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Asignaturas")) {
            while (rs.next()) {
                listAsignaturas.add(crearAsignaturas(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAsignaturas;
    }

    @Override
    public Asignaturas porNombre(String nombreAsignatura) {
        Asignaturas asignaturas = null;
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Asignaturas WHERE nombreAsignatura =?")) {
            stmt.setString(1, nombreAsignatura);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    asignaturas = crearAsignaturas(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asignaturas;
    }

    @Override
    public void crear(Asignaturas asignaturas) {

        String sql = "INSERT INTO Asignaturas(nombreAsignatura,cantidadCreditos,programaId,periodoId,cursoId,profesorId) VALUES(?,?,?,?,?,?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, asignaturas.getNombreAsignatura());
            stmt.setInt(2, asignaturas.getCantidadCreditos());
            stmt.setInt(3, asignaturas.getProgramaId());
            stmt.setInt(4, asignaturas.getPeriodoId());
            stmt.setInt(5, asignaturas.getCursoId());
            stmt.setInt(6, asignaturas.getProfesorId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Asignaturas asignaturas) {
        String sql = "UPDATE Asignaturas SET nombreAsignatura=?, cantidadCreditos=?, programaId=?, periodoId=?, cursoId=?, profesorId=? WHERE idAsignatura=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, asignaturas.getNombreAsignatura());
            stmt.setInt(2, asignaturas.getCantidadCreditos());
            stmt.setInt(3, asignaturas.getProgramaId());
            stmt.setInt(4, asignaturas.getPeriodoId());
            stmt.setInt(5, asignaturas.getCursoId());
            stmt.setInt(6, asignaturas.getProfesorId());
            stmt.setInt(7, asignaturas.getIdAsignatura());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Asignaturas asignaturas) {
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Asignaturas WHERE idAsignatura=?")) {
            stmt.setInt(1, asignaturas.getIdAsignatura());
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    private Asignaturas crearAsignaturas(ResultSet rs) throws SQLException {
        Asignaturas asignaturas = new Asignaturas();
        asignaturas.setIdAsignatura(rs.getInt("idAsignatura"));
        asignaturas.setNombreAsignatura(rs.getString("nombreAsignatura"));
        asignaturas.setCantidadCreditos(rs.getInt("cantidadCreditos"));
        asignaturas.setProgramaId(rs.getInt("programaId"));
        asignaturas.setPeriodoId(rs.getInt("periodoId"));
        asignaturas.setCursoId(rs.getInt("cursoId"));
        asignaturas.setProfesorId(rs.getInt("profesorId"));
        return asignaturas;
    }

}
