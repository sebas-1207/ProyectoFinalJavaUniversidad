package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.profesorexceptions.ProfesorNullException;
import com.campusland.repository.RepositoryProfesor;
import com.campusland.repository.models.Personas;
import com.campusland.repository.models.Profesores;
import com.campusland.services.ServiceProfesor;

public class ServiceProfesorImpl implements ServiceProfesor {

    private final RepositoryProfesor crudRepositoryProfesor;

    public ServiceProfesorImpl(RepositoryProfesor crudRepositoryProfesor){
        this.crudRepositoryProfesor = crudRepositoryProfesor;
    }

    @Override
    public List<Personas> listar() {
        return this.crudRepositoryProfesor.listar();
    }

    @Override
    public Personas porDocumento(String numeroDocumento) throws ProfesorNullException {
        Personas personas = this.crudRepositoryProfesor.porDocumento(numeroDocumento);
        if(personas!=null){
            return personas;
        }else{
            throw new ProfesorNullException("No se econtro el numero de Documento del Profesor");
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
