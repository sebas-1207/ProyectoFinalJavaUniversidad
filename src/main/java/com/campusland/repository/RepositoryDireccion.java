package com.campusland.repository;

import java.util.List;

import com.campusland.exceptiones.direccionexceptions.DireccionNullException;
import com.campusland.repository.models.Direccion;

public interface RepositoryDireccion {
    
    List<Direccion> listar();

    void crear(Direccion direccion) throws DireccionNullException;
    
}
