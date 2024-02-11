package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.alumnoexceptions.AlumnoNullException;
import com.campusland.respository.RepositoryAlumno;
import com.campusland.respository.models.Alumnos;
import com.campusland.services.ServiceAlumno;

public class ServiceAlumnoImpl implements ServiceAlumno{

    private final RepositoryAlumno crudRepositoryAlumno;

    public ServiceAlumnoImpl(RepositoryAlumno crudRepositoryAlumno){
        this.crudRepositoryAlumno=crudRepositoryAlumno;
    }

    @Override
    public List<Alumnos> listar() {
        return this.crudRepositoryAlumno.listar();
    }

    @Override
    public Alumnos porDocumento(String numeroDocumento) throws AlumnoNullException {
        Alumnos alumnos = this.crudRepositoryAlumno.porDocumento(numeroDocumento);
        if(alumnos!=null){
            return alumnos;
        }else{
            throw new AlumnoNullException("No se encontro alumno por su numero de documento");
        }
    }

    @Override
    public void crear(Alumnos alumnos) {
        this.crudRepositoryAlumno.crear(alumnos);
    }

    @Override
    public void editar(Alumnos alumnos) {
        this.crudRepositoryAlumno.editar(alumnos);
    }

    @Override
    public void eliminar(Alumnos alumnos) {
        this.crudRepositoryAlumno.eliminar(alumnos);
    }

    
}
