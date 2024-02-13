package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.asignaturasexception.AsignaturaNullException;
import com.campusland.repository.models.Asignaturas;

public interface ServiceAsignatura {
    
    List<Asignaturas> listar();

    Asignaturas porNombre(String nombreAsignatura) throws AsignaturaNullException;

    void crear(Asignaturas asignaturas);

    void editar(Asignaturas asignaturas);
    
    void eliminar(Asignaturas asignaturas);
    
}
