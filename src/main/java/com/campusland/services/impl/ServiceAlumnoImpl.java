package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.alumnoexceptions.AlumnoNullException;
import com.campusland.repository.RepositoryAlumno;
import com.campusland.repository.models.Alumnos;
import com.campusland.repository.models.Personas;
import com.campusland.services.ServiceAlumno;

public class ServiceAlumnoImpl implements ServiceAlumno{

    private final RepositoryAlumno crudRepositoryAlumno;

    public ServiceAlumnoImpl(RepositoryAlumno crudRepositoryAlumno){
        this.crudRepositoryAlumno=crudRepositoryAlumno;
    }

    @Override
    public List<Personas> listar() {
        return this.crudRepositoryAlumno.listar();
    }

    @Override
    public Personas porDocumento(String numeroDocumento) throws AlumnoNullException {
        Personas personas = this.crudRepositoryAlumno.porDocumento(numeroDocumento);
        if(personas!=null){
            return personas;
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
