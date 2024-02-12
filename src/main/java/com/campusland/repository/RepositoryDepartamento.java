package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Departamentos;

public interface RepositoryDepartamento {
    
    List<Departamentos> listar();

    Departamentos porNombre(String nombreDepartamento);

    void crear(Departamentos departamentos);

    void editar(Departamentos departamentos);

    void eliminar(Departamentos departamentos);
    
}
