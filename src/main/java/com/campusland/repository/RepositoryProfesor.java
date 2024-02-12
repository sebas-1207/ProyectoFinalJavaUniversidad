package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Personas;
import com.campusland.repository.models.Profesores;

public interface RepositoryProfesor {
    
    List<Personas> listar();

    Personas porDocumento(String numeroDocumento);

    void crear(Profesores profesores);

    void editar(Profesores profesores);

    void eliminar(Profesores profesores);
}
