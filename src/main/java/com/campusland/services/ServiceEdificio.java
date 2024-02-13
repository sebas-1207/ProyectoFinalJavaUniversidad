package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.edificiosexceptions.EdificioNullException;
import com.campusland.repository.models.Edificios;

public interface ServiceEdificio {
    
    List<Edificios> listar();

    Edificios porNumero(int numeroEdificio) throws EdificioNullException;

    void crear(Edificios edificio);
}
