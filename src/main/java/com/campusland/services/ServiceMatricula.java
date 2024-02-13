package com.campusland.services;

import java.util.List;

import com.campusland.repository.models.Matriculas;

public interface ServiceMatricula {
    
    List<Matriculas> listar();

    void crear(Matriculas matriculas);
    
}
