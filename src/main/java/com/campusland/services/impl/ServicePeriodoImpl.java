package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.periodoexceptions.PeriodoNullException;
import com.campusland.repository.RepositoryPeriodo;
import com.campusland.repository.models.Periodo;
import com.campusland.services.ServicePeriodo;

public class ServicePeriodoImpl implements ServicePeriodo {

    private final RepositoryPeriodo crudRepositoryPeriodo;

    public ServicePeriodoImpl(RepositoryPeriodo crudRepositoryPeriodo) {
        this.crudRepositoryPeriodo = crudRepositoryPeriodo;
    }

    @Override
    public List<Periodo> listar() {
        return this.crudRepositoryPeriodo.listar();
    }

    @Override
    public Periodo porCodigo(int codigoEspecifico) throws PeriodoNullException {
        Periodo periodo = this.crudRepositoryPeriodo.porCodigo(codigoEspecifico);
        if (periodo != null) {
            return periodo;
        } else {
            throw new PeriodoNullException("No se encontro periodo por codigo");
        }
    }

    @Override
    public void crear(Periodo periodo) {
        this.crudRepositoryPeriodo.crear(periodo);
    }

    @Override
    public void editar(Periodo periodo) {
        this.crudRepositoryPeriodo.editar(periodo);
    }

    @Override
    public void eliminar(Periodo periodo) {
        this.crudRepositoryPeriodo.eliminar(periodo);
    }

}
