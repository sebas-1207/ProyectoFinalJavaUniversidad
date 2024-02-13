package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.edificiosexceptions.EdificioNullException;
import com.campusland.repository.RepositoryEdificio;
import com.campusland.repository.models.Edificios;
import com.campusland.services.ServiceEdificio;

public class ServiceEdificioImpl implements ServiceEdificio{

    private final RepositoryEdificio crudRepositoryEdificio;

    public ServiceEdificioImpl(RepositoryEdificio crudRepositoryEdificio){
        this.crudRepositoryEdificio=crudRepositoryEdificio;
    }

    @Override
    public List<Edificios> listar() {
        return this.crudRepositoryEdificio.listar();
    }

    @Override
    public Edificios porNumero(int numeroEdificio) throws EdificioNullException{
        Edificios edificio = this.crudRepositoryEdificio.porNumero(numeroEdificio);
        if (edificio!= null) {
            return edificio;
        } else {
            throw new EdificioNullException("No se encontro edificio por numero");
        }

    }

    @Override
    public void crear(Edificios edificio) {
        this.crudRepositoryEdificio.crear(edificio);
    }
    

}
