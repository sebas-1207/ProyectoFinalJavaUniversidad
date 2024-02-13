package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Matriculas;

public interface RepositoryMatriculas {
    
    List<Matriculas> listar();

    void crear(Matriculas matriculas);

    void eliminar(Matriculas matriculas);
    
}
