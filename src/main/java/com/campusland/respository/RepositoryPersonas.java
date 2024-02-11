package com.campusland.respository;

import java.util.List;

import com.campusland.respository.models.Personas;

public interface RepositoryPersonas {

    List<Personas> listar();
    
    Personas porDocumento(String numeroDocumento);

    void crear(Personas personas);

    void editar(Personas personas);

    void eliminar(Personas personas);
    
}
