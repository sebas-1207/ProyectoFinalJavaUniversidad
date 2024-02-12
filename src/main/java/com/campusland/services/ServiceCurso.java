package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.cursoexceptions.CursoNullException;
import com.campusland.repository.models.Cursos;

public interface ServiceCurso {
    
    
    List<Cursos> listar();

    Cursos porNombre(String nombreCurso)throws CursoNullException ;

    void crear(Cursos cursos);

    void editar(Cursos cursos);

    void eliminar(Cursos cursos);
}
