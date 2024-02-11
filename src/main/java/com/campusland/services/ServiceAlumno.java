package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.alumnoexceptions.AlumnoNullException;
import com.campusland.respository.models.Alumnos;

public interface ServiceAlumno {
    
    List<Alumnos> listar();

    Alumnos porDocumento(String numeroDocumento) throws AlumnoNullException;

    void crear(Alumnos alumnos);

    void editar(Alumnos alumnos);

    void eliminar(Alumnos alumnos);
}
