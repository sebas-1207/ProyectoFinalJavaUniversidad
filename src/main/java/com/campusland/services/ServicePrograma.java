package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.programaexceptions.ProgramaNullException;
import com.campusland.repository.models.Programas;

public interface ServicePrograma {
    
    List<Programas> listar();

    Programas porNombre(String nombrePrograma) throws ProgramaNullException;

    void crear(Programas programas);

    void editar(Programas programas);

    void eliminar(Programas programas);
    
}
