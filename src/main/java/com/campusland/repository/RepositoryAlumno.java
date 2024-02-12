package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Alumnos;
import com.campusland.repository.models.Personas;

public interface RepositoryAlumno {
    

    List<Personas> listar();

    Personas porDocumento(String numeroDocumento);

    void crear(Alumnos alumnos);

    void editar(Alumnos alumnos);

    void eliminar(Alumnos alumnos);
    
}
