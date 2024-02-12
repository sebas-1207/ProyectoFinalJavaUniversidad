package com.campusland.repository.impl.impldepartamento;

import java.util.List;

import com.campusland.repository.RepositoryDepartamento;
import com.campusland.repository.models.Departamentos;
import com.campusland.utils.conexionpersistencia.conexionbdjson.ConexionBDJsonDepartamentos;

public class RepositoryDepartamentoJsonImpl implements RepositoryDepartamento {

    ConexionBDJsonDepartamentos conexion = ConexionBDJsonDepartamentos.getConexion();

    @Override
    public List<Departamentos> listar() {
        return conexion.getData(Departamentos.class);
    }

    @Override
    public Departamentos porNombre(String nombreDepartamento) {
        Departamentos resultado = null;
        for (Departamentos departamentos : conexion.getData(Departamentos.class)) {
            if (departamentos.getNombreDepartamento().equals(nombreDepartamento)) {
                resultado = departamentos;
                break;
            }
        }
        return resultado;
    }

    @Override
    public void crear(Departamentos departamentos) {
        List<Departamentos> listDepartamentos = conexion.getData(Departamentos.class);
        listDepartamentos.add(departamentos);
        conexion.saveData(listDepartamentos);
    }

    @Override
    public void editar(Departamentos departamentos) {
        List<Departamentos> listDepartamentos = conexion.getData(Departamentos.class);
        boolean change = false;
        for (Departamentos departamentosCurrent : listDepartamentos) {
            if (departamentosCurrent.getNombreDepartamento().equals(departamentos.getNombreDepartamento())) {
                departamentosCurrent.setNombreDepartamento((departamentos.getNombreDepartamento()));
                change = true;
                break;
            }
        }
        if (change)
            conexion.saveData(listDepartamentos);
    }

    @Override
    public void eliminar(Departamentos depar) {
        List<Departamentos> listDepartamentos = conexion.getData(Departamentos.class);
        boolean change = false;
        for (Departamentos departamentos : listDepartamentos) {
            if (departamentos.getIdDepartamento() == depar.getIdDepartamento()) {
                listDepartamentos.remove(departamentos);
                change = true;
                break;
            }

        }
        if (change)
            conexion.saveData(listDepartamentos);
    }
}
