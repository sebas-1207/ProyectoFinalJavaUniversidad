package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.direccionexceptions.DireccionNullException;
import com.campusland.repository.RepositoryDireccion;
import com.campusland.repository.models.Direccion;
import com.campusland.services.ServiceDireccion;

public class ServiceDireccionImpl implements ServiceDireccion {

    private final RepositoryDireccion crudRepositoryDireccion;

    public ServiceDireccionImpl(RepositoryDireccion crudRepositoryDireccion) {
        this.crudRepositoryDireccion = crudRepositoryDireccion;
    }

    @Override
    public List<Direccion> listar() {
        return this.crudRepositoryDireccion.listar();
    }

    @Override
    public void crear(Direccion direccion) {
        try {
            this.crudRepositoryDireccion.crear(direccion);
        } catch (DireccionNullException e) {
            e.printStackTrace();
        }
    }

}
