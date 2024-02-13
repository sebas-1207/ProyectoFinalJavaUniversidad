package com.campusland.services.impl;

import java.util.List;

import com.campusland.repository.RepositoryMatriculas;
import com.campusland.repository.models.Matriculas;
import com.campusland.services.ServiceMatricula;

public class ServiceMatriculaImpl implements ServiceMatricula{

    private final RepositoryMatriculas crudRepositoryMatriculas;

    public ServiceMatriculaImpl(RepositoryMatriculas crudRepositoryMatriculas){
        this.crudRepositoryMatriculas = crudRepositoryMatriculas;
    }

    @Override
    public List<Matriculas> listar() {
        return this.crudRepositoryMatriculas.listar();
    }

    @Override
    public void crear(Matriculas matriculas) {
        this.crudRepositoryMatriculas.crear(matriculas);
    }
    
}
