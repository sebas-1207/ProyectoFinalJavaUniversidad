package com.campusland.repository.impl.implcurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.repository.RepositoryCursos;
import com.campusland.repository.models.Cursos;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryCursoMysqlImpl implements RepositoryCursos {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Cursos> listar() {
        List<Cursos> listCursos = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Cursos")) {
            while (rs.next()) {
                listCursos.add(crearCursos(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCursos;
    }

    @Override
    public Cursos porNombre(String nombreCurso) {
        Cursos cursos = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cursos WHERE nombreCurso=?")) {
            stmt.setString(1, nombreCurso);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cursos = crearCursos(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public void crear(Cursos cursos) {
        String sql = "INSERT INTO Cursos(nombreCurso, guiaCatedra) VALUES(?,?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cursos.getNombreCurso());
            stmt.setString(2, cursos.getGuiaCatedra());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Cursos cursos) {
        String sql = "UPDATE Cursos SET nombreCurso=?, guiaCatedra=? WHERE idCurso=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cursos.getNombreCurso());
            stmt.setString(2, cursos.getGuiaCatedra());
            stmt.setInt(3, cursos.getIdCurso());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Cursos cursos) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Cursos WHERE idCurso=?")) {
            stmt.setInt(1, cursos.getIdCurso());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Cursos crearCursos(ResultSet rs) throws SQLException {
        Cursos cursos = new Cursos();
        cursos.setIdCurso(rs.getInt("idCurso"));
        cursos.setNombreCurso(rs.getString("nombreCurso"));
        cursos.setGuiaCatedra(rs.getString("guiaCatedra"));
        return cursos;

    }

}
