package com.campusland.repository.impl.implperiodo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.campusland.repository.RepositoryPeriodo;
import com.campusland.repository.models.Periodo;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryPeriodoMysqlImpl implements RepositoryPeriodo {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Periodo> listar() {
        List<Periodo> listPeriodo = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Periodo")) {
            while (rs.next()) {
                listPeriodo.add(crearPeriodo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPeriodo;
    }

    @Override
    public Periodo porCodigo(int codigoEspecifico) {
        Periodo periodo = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Periodo WHERE codigoEspecifico=?")) {
            stmt.setInt(1, codigoEspecifico);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    periodo = crearPeriodo(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return periodo;
    }

    @Override
    public void crear(Periodo periodo) {
        String sql = "INSERT INTO Periodo(codigoEspecifico, año, semestre) VALUES(?,?,?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, periodo.getCodigoEspecifico());
            stmt.setInt(2, periodo.getAño().getValue());
            stmt.setInt(3, periodo.getSemestre());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Periodo periodo) {
        String sql = "UPDATE Periodo SET codigoEspecifico=?, año=?, semestre=? WHERE idPeriodo=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, periodo.getCodigoEspecifico());
            stmt.setInt(2, periodo.getAño().getValue());
            stmt.setInt(3, periodo.getSemestre());
            stmt.setInt(4, periodo.getIdPeriodo());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Periodo periodo) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Periodo WHERE idPeriodo=?")) {
            stmt.setInt(1, periodo.getIdPeriodo());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Periodo crearPeriodo(ResultSet rs) throws SQLException {
        Periodo periodo = new Periodo();
        periodo.setIdPeriodo(rs.getInt("idPeriodo"));
        periodo.setCodigoEspecifico(rs.getInt("codigoEspecifico"));
        int año = rs.getInt("año");
        Year year = Year.of(año);
        periodo.setAño(year);
        periodo.setSemestre(rs.getInt("semestre"));
        return periodo;

    }

}
