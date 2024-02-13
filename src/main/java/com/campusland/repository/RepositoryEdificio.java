package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Edificios;

public interface RepositoryEdificio {
    
    List<Edificios> listar();

    Edificios porNumero(int numeroEdificio);

    void crear(Edificios edificio);

}
