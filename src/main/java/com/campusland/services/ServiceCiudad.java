package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.ciudadexceptions.CiudadNullException;
import com.campusland.repository.models.Ciudad;

public interface ServiceCiudad {
    
    List<Ciudad> listar();

    Ciudad porNombre(String nombreCiudad) throws CiudadNullException;

    void crear(Ciudad ciudad);

    void editar(Ciudad ciudad);
    
}

