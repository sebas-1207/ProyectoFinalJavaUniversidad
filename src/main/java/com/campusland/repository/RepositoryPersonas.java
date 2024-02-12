package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Personas;

public interface RepositoryPersonas {

    List<Personas> listar();
    
    Personas porDocumento(String numeroDocumento);

    void crear(Personas personas);

    void editar(Personas personas);

    void eliminar(Personas personas);
    
}
