package com.campusland.respository;

import java.util.List;

import com.campusland.respository.models.Cursos;

public interface RepositoryCursos {

    List<Cursos> listar();

    Cursos porNombre(String nombreCurso);

    void crear(Cursos cursos);

    void editar(Cursos cursos);

    void eliminar(Cursos cursos);
}
