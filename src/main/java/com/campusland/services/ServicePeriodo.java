package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.periodoexceptions.PeriodoNullException;
import com.campusland.repository.models.Periodo;

public interface ServicePeriodo {

    List<Periodo> listar();

    Periodo porCodigo(int codigoEspecifico) throws PeriodoNullException;

    void crear(Periodo periodo);

    void editar(Periodo periodo);

    void eliminar(Periodo periodo);
}
