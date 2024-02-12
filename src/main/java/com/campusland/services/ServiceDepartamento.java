package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.departamentosexceptions.DepartamentoNullException;
import com.campusland.repository.models.Departamentos;

public interface ServiceDepartamento {

    List<Departamentos> listar();

    Departamentos porNombre(String nombreDepartamento) throws DepartamentoNullException;

    void crear(Departamentos departamentos);

    void editar(Departamentos departamentos);

    void eliminar(Departamentos departamentos);
}
