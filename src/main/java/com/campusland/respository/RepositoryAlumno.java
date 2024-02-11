package com.campusland.respository;

import java.util.List;

import com.campusland.respository.models.Alumnos;

public interface RepositoryAlumno {
    

    List<Alumnos> listar();

    Alumnos porDocumento(String numeroDocumento);

    void crear(Alumnos alumnos);

    void editar(Alumnos alumnos);

    void eliminar(Alumnos alumnos);
    
}
