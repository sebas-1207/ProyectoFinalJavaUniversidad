package com.campusland.respository;

import java.util.List;

import com.campusland.respository.models.Profesores;

public interface RepositoryProfesor {
    
    List<Profesores> listar();

    Profesores porDocumento(String numeroDocumento);

    void crear(Profesores profesores);

    void editar(Profesores profesores);

    void eliminar(Profesores idProfesor);
}
