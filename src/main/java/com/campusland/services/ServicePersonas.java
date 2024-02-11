package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.personaexceptions.PersonaNullException;
import com.campusland.respository.models.Personas;

public interface ServicePersonas {
    
    List<Personas> listar();
    
    Personas porDocumento(String numeroDocumento) throws PersonaNullException;

    void crear(Personas personas);

    void editar(Personas personas);

    void eliminar(Personas idPersona);
}
