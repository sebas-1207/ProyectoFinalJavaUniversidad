package com.campusland.repository.impl.implmatricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.repository.RepositoryMatriculas;
import com.campusland.repository.models.Asignaturas;
import com.campusland.repository.models.Matriculas;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryMatriculaMysqlImpl implements RepositoryMatriculas {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Matriculas> listar() {
        List<Matriculas> listMatriculas = new ArrayList<>();
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Matriculas")) {
            while (rs.next()) {
                listMatriculas.add(crearMatriculas(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listMatriculas;
    }

    @Override
    public void crear(Matriculas matriculas) {

        String sql = "INSERT INTO Matriculas(alumnoId,asignaturaId) VALUES(?,?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matriculas.getAlumnoId());
            stmt.setInt(2, matriculas.getAsignaturaId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Matriculas matriculas) {
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Matriculas WHERE idMatricula=?")) {
            stmt.setInt(1, matriculas.getIdMatricula());
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    private Matriculas crearMatriculas(ResultSet rs) throws SQLException {
        Matriculas matriculas = new Matriculas();
        matriculas.setAlumnoId(rs.getInt("alumnoId"));
        matriculas.setAsignaturaId(rs.getInt("asignaturaId"));
        return matriculas;
    }

}
