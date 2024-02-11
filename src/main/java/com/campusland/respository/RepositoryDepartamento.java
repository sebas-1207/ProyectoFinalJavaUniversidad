package com.campusland.respository;

import java.util.List;

import com.campusland.respository.models.Departamentos;

public interface RepositoryDepartamento {
    
    List<Departamentos> listar();

    Departamentos porNombre(String nombreDepartamento);

    void crear(Departamentos departamentos);

    void editar(Departamentos departamentos);

    void eliminar(Departamentos departamentos);
    
}
