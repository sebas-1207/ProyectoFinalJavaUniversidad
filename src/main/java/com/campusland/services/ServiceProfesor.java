package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.profesorexceptions.ProfesorNullException;
import com.campusland.respository.models.Profesores;

public interface ServiceProfesor {
    
    List<Profesores> listar();

    Profesores porDocumento(String numeroDocumento) throws ProfesorNullException;

    void crear(Profesores profesores);

    void editar(Profesores profesores);

    void eliminar(Profesores profesores);
}
