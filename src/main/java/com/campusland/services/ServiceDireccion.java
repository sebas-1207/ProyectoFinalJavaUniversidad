package com.campusland.services;

import java.util.List;

import com.campusland.repository.models.Direccion;

public interface ServiceDireccion {
    
    List<Direccion> listar();

    void crear(Direccion direccion);
}
