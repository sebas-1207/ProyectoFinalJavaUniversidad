package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.departamentosexceptions.DepartamentoNullException;
import com.campusland.repository.RepositoryDepartamento;
import com.campusland.repository.models.Departamentos;
import com.campusland.services.ServiceDepartamento;

public class ServiceDepartamentoImpl implements ServiceDepartamento {

    private final RepositoryDepartamento cruRepositoryDepartamento;

    public ServiceDepartamentoImpl(RepositoryDepartamento cruRepositoryDepartamento) {
        this.cruRepositoryDepartamento = cruRepositoryDepartamento;
    }

    @Override
    public List<Departamentos> listar() {
        return this.cruRepositoryDepartamento.listar();
    }

    @Override
    public Departamentos porNombre(String nombreDepartamento) throws DepartamentoNullException {
        Departamentos departamentos = this.cruRepositoryDepartamento.porNombre(nombreDepartamento);
        if (departamentos != null) {
            return departamentos;
        } else {
            throw new DepartamentoNullException("No se encontro departamento por nombre");
        }
    }

    @Override
    public void crear(Departamentos departamentos) {
        this.cruRepositoryDepartamento.crear(departamentos);
    }

    @Override
    public void editar(Departamentos departamentos) {
        this.cruRepositoryDepartamento.editar(departamentos);
    }

    @Override
    public void eliminar(Departamentos departamentos) {
        this.cruRepositoryDepartamento.eliminar(departamentos);
    }

}
