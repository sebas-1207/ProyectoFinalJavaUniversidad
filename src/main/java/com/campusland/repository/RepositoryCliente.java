package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Cliente;

public interface RepositoryCliente {

    List<Cliente> listar();

    Cliente porDocumento(String documento);

    void crear(Cliente cliente);

    void editar(Cliente cliente);

    void eliminar(Cliente cliente);

}
