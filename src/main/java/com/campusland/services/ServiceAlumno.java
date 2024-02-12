package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.alumnoexceptions.AlumnoNullException;
import com.campusland.repository.models.Alumnos;
import com.campusland.repository.models.Personas;

public interface ServiceAlumno {

    List<Personas> listar();

    Personas porDocumento(String numeroDocumento) throws AlumnoNullException;

    void crear(Alumnos alumnos);

    void editar(Alumnos alumnos);

    void eliminar(Alumnos alumnos);
}
