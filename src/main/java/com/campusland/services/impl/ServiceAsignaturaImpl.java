package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.asignaturasexception.AsignaturaNullException;
import com.campusland.repository.RepositoryAsignaturas;
import com.campusland.repository.models.Asignaturas;
import com.campusland.services.ServiceAsignatura;

public class ServiceAsignaturaImpl implements ServiceAsignatura {

    private final RepositoryAsignaturas crudRepositoryAsignaturas;

    public ServiceAsignaturaImpl(RepositoryAsignaturas crudRepositoryAsignaturas){
        this.crudRepositoryAsignaturas=crudRepositoryAsignaturas;
    }

    @Override
    public List<Asignaturas> listar() {
        return this.crudRepositoryAsignaturas.listar();
    }

    @Override
    public Asignaturas porNombre(String nombreAsignatura) throws AsignaturaNullException {
        Asignaturas asignaturas = this.crudRepositoryAsignaturas.porNombre(nombreAsignatura);
        if (asignaturas!= null){
            return asignaturas;
        }else{
            throw new AsignaturaNullException("No se encontro la asignatura por el nombre");
        }
    }

    @Override
    public void crear(Asignaturas asignaturas) {
        this.crudRepositoryAsignaturas.crear(asignaturas);
    }

    @Override
    public void editar(Asignaturas asignaturas) {
        this.crudRepositoryAsignaturas.editar(asignaturas);
    }

    @Override
    public void eliminar(Asignaturas asignaturas) {
        this.crudRepositoryAsignaturas.eliminar(asignaturas);
    }

}
