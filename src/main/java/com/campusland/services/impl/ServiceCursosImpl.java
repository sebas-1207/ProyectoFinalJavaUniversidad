package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.cursoexceptions.CursoNullException;
import com.campusland.repository.RepositoryCursos;
import com.campusland.repository.models.Cursos;
import com.campusland.services.ServiceCurso;

public class ServiceCursosImpl implements ServiceCurso {

    private final RepositoryCursos crudRepositoryCursos;

    public ServiceCursosImpl(RepositoryCursos crudRepositoryCursos) {
        this.crudRepositoryCursos = crudRepositoryCursos;
    }

    @Override
    public List<Cursos> listar() {
        return this.crudRepositoryCursos.listar();
    }

    @Override
    public Cursos porNombre(String nombreCurso) throws CursoNullException {
        Cursos cursos = this.crudRepositoryCursos.porNombre(nombreCurso);
        if (cursos != null){
            return cursos;
        }else{
            throw new CursoNullException("No se encontro curso por nombre");
        }
    }

    @Override
    public void crear(Cursos cursos) {
        this.crudRepositoryCursos.crear(cursos);
    }

    @Override
    public void editar(Cursos cursos) {
        this.crudRepositoryCursos.crear(cursos);
    }

    @Override
    public void eliminar(Cursos cursos) {
        this.crudRepositoryCursos.eliminar(cursos);
    }

}
