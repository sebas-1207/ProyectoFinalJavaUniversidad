package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Programas;

public interface RepositoryPrograma {

    List<Programas> listar();

    Programas porNombre(String nombrePrograma);

    void crear(Programas programas);

    void editar(Programas programas);

    void eliminar(Programas programas);

}
