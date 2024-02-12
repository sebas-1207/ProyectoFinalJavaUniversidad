package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Ciudad;

public interface RepositoryCiudad {

    List<Ciudad> listar();

    Ciudad porNombre(String nombreCiudad);

    void crear(Ciudad ciudad);

    void editar(Ciudad ciudad);
}
