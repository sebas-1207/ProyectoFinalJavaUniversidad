package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.profesorexceptions.ProfesorNullException;
import com.campusland.respository.RepositoryProfesor;
import com.campusland.respository.models.Profesores;
import com.campusland.services.ServiceProfesor;

public class ServiceProfesorImpl implements ServiceProfesor {

    private final RepositoryProfesor crudRepositoryProfesor;

    public ServiceProfesorImpl(RepositoryProfesor crudRepositoryProfesor){
        this.crudRepositoryProfesor = crudRepositoryProfesor;
    }

    @Override
    public List<Profesores> listar() {
        return this.crudRepositoryProfesor.listar();
    }

    @Override
    public Profesores porDocumento(String numeroDocumento) throws ProfesorNullException {
        Profesores profesores = this.crudRepositoryProfesor.porDocumento(numeroDocumento);
        if (profesores != null){
            return profesores;
        }else{
            throw new ProfesorNullException("No se encontro el profesor por el numero de documento");
        }
    }

    @Override
    public void crear(Profesores profesores) {
        this.crudRepositoryProfesor.crear(profesores);
    }

    @Override
    public void editar(Profesores profesores) {
        this.crudRepositoryProfesor.editar(profesores);
    }

    @Override
    public void eliminar(Profesores profesores) {
        this.crudRepositoryProfesor.eliminar(profesores);
    }
    
}
