package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.programaexceptions.ProgramaNullException;
import com.campusland.repository.RepositoryPrograma;
import com.campusland.repository.models.Programas;
import com.campusland.services.ServicePrograma;

public class ServiceProgramaImpl implements ServicePrograma{

    private final RepositoryPrograma crudRepositoryPrograma;

    public ServiceProgramaImpl(RepositoryPrograma crudRepositoryPrograma) {
        this.crudRepositoryPrograma = crudRepositoryPrograma;
    }

    @Override
    public List<Programas> listar() {
        return this.crudRepositoryPrograma.listar();
    }

    @Override
    public Programas porNombre(String nombrePrograma) throws ProgramaNullException {
        Programas programas = this.crudRepositoryPrograma.porNombre(nombrePrograma);
        if (programas != null){
            return programas;
        }else{
            throw new ProgramaNullException("No se encontro el programa por el nombre");
        }
    }

    @Override
    public void crear(Programas programas) {
        this.crudRepositoryPrograma.crear(programas);
    }

    @Override
    public void editar(Programas programas) {
        this.crudRepositoryPrograma.editar(programas);
    }

    @Override
    public void eliminar(Programas programas) {
        this.crudRepositoryPrograma.eliminar(programas);
    }
}
