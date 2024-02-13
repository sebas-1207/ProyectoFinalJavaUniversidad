package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.salonexceptions.SalonesNullException;
import com.campusland.repository.models.Salones;

public interface ServiceSalon {
    
    List<Salones> listar();

    Salones porNumero(int numeroIdentificacion) throws SalonesNullException;

    void crear(Salones salones);
    
    void editar(Salones salones);
    
    void eliminar(Salones salones);
}
