package com.campusland.repository.impl.implEdificio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.repository.RepositoryEdificio;
import com.campusland.repository.models.Edificios;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryEdificioMysqlImpl implements RepositoryEdificio {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Edificios> listar() {
        List<Edificios> listEdificios = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Edificios")) {
            while (rs.next()) {
                listEdificios.add(crearEdificio(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listEdificios;
    }

    @Override
    public Edificios porNumero(int numeroEdificio) {
        Edificios edificios = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Edificios WHERE numeroEdificio=?")) {
            stmt.setInt(1, numeroEdificio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    edificios = crearEdificio(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return edificios;
    }

    @Override
    public void crear(Edificios edificio) {
        String sql = "INSERT INTO Edificios(numeroEdificio, numeroPiso,capacidadMax_salones) VALUES(?,?,?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, edificio.getNumeroEdificio());
            stmt.setInt(2, edificio.getNumeroPiso());
            stmt.setInt(3, edificio.getCapacidadMax_Salones());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Edificios crearEdificio(ResultSet rs) throws SQLException {
        Edificios edificios = new Edificios();
        edificios.setIdEdificios(rs.getInt("idEdificio"));
        edificios.setNumeroEdificio(rs.getInt("numeroEdificio"));
        edificios.setNumeroPiso(rs.getInt("numeroPiso"));
        edificios.setCapacidadMax_Salones(rs.getInt("capacidadMax_salones"));
        return edificios;

    }

}
