package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.personaexceptions.PersonaNullException;
import com.campusland.repository.RepositoryPersonas;
import com.campusland.repository.models.Personas;
import com.campusland.services.ServicePersonas;

public class ServicePersonasImpl implements ServicePersonas{

    private final RepositoryPersonas crudRepositoryPersonas;

    public ServicePersonasImpl(RepositoryPersonas crudRepositoryPersonas){
        this.crudRepositoryPersonas=crudRepositoryPersonas;
    }

    @Override
    public List<Personas> listar() {
        return this.crudRepositoryPersonas.listar();
    }

    @Override
    public Personas porDocumento(String numeroDocumento) throws PersonaNullException{
        Personas personas = this.crudRepositoryPersonas.porDocumento(numeroDocumento);
        if(personas != null){
            return personas;
        }else{
            throw new PersonaNullException("No se encontro la persona por su numero de documento");
        }
    }

    @Override
    public void crear(Personas personas) {
        this.crudRepositoryPersonas.crear(personas);
    }

    @Override
    public void editar(Personas personas) {
        this.crudRepositoryPersonas.editar(personas);
    }

    @Override
    public void eliminar(Personas personas) {
        this.crudRepositoryPersonas.eliminar(personas);
    }

    

    
}
