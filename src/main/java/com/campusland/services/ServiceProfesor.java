package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.profesorexceptions.ProfesorNullException;
import com.campusland.repository.models.Personas;
import com.campusland.repository.models.Profesores;

public interface ServiceProfesor {
    
    List<Personas> listar();

    Personas porDocumento(String numeroDocumento) throws ProfesorNullException;

    void crear(Profesores profesores);

    void editar(Profesores profesores);

    void eliminar(Profesores profesores);
}
