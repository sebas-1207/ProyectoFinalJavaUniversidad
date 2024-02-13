package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.salonexceptions.SalonesNullException;
import com.campusland.repository.RepositorySalon;
import com.campusland.repository.models.Salones;
import com.campusland.services.ServiceSalon;

public class ServiceSalonImpl implements ServiceSalon {

    private final RepositorySalon crudRepositorySalon;

    public ServiceSalonImpl(RepositorySalon crudRepositorySalon) {
        this.crudRepositorySalon = crudRepositorySalon;
    }

    @Override
    public List<Salones> listar() {
        return this.crudRepositorySalon.listar();
    }

    @Override
    public Salones porNumero(int numeroIdentificacion) throws SalonesNullException {
        Salones salones = this.crudRepositorySalon.porNumero(numeroIdentificacion);
        if (salones!= null) {
            return salones;
        } else {
            throw new SalonesNullException("No se encontro salon por numero");
        }
    }

    @Override
    public void crear(Salones salones) {
        this.crudRepositorySalon.crear(salones);
    }

    @Override
    public void editar(Salones salones) {
        this.crudRepositorySalon.editar(salones);
    }

    @Override
    public void eliminar(Salones salones) {
        this.crudRepositorySalon.eliminar(salones);
    }
    
}
