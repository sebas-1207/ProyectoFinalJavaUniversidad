package com.campusland.repository;

import java.util.List;

import com.campusland.repository.models.Periodo;

public interface RepositoryPeriodo {

    List<Periodo> listar();

    Periodo porCodigo(int codigoEspecifico);

    void crear(Periodo periodo);

    void editar(Periodo periodo);

    void eliminar(Periodo periodo);

}
