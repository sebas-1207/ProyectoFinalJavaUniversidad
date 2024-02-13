package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Salones;

public interface RepositorySalon {
    
    List<Salones> listar();

    Salones porNumero(int numeroIdentificacion);

    void crear(Salones salones);
    
    void editar(Salones salones);
    
    void eliminar(Salones salones);
    
}
