package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Producto;

public interface RepositoryProducto {

    List<Producto> listar();

    Producto porCodigo(int codigo);

    void crear(Producto producto);

    void editar(Producto producto);

    void eliminar(Producto codigo);
    
    
}
