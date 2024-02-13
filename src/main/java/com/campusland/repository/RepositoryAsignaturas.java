package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Asignaturas;

public interface RepositoryAsignaturas {
    
    List<Asignaturas> listar();

    Asignaturas porNombre(String nombreAsignatura);
    
    void crear(Asignaturas asignaturas);
    
    void editar(Asignaturas asignaturas);
    
    void eliminar(Asignaturas asignaturas);
}
