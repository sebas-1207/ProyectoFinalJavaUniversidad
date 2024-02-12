package com.campusland.repository.impl.implcurso;

import java.util.List;

import com.campusland.repository.RepositoryCursos;
import com.campusland.repository.models.Cursos;
import com.campusland.utils.conexionpersistencia.conexionbdjson.ConexionBDJsonCursos;

public class RepositoryCursoJsonImpl implements RepositoryCursos {

    ConexionBDJsonCursos conexion = ConexionBDJsonCursos.getConexion();

    @Override
    public List<Cursos> listar() {
        return conexion.getData(Cursos.class);
    }

    @Override
    public Cursos porNombre(String nombreCurso) {
        Cursos resultado = null;
        for (Cursos cursos : conexion.getData(Cursos.class)) {
            if (cursos.getNombreCurso().equals(nombreCurso)) {
                resultado = cursos;
                break;
            }
        }
        return resultado;
    }

    @Override
    public void crear(Cursos cursos) {
        List<Cursos> listCursos = conexion.getData(Cursos.class);
        listCursos.add(cursos);
        conexion.saveData(listCursos);
    }

    @Override
    public void editar(Cursos cursos) {
        List<Cursos> listCursos = conexion.getData(Cursos.class);
        boolean change = false;
        for (Cursos cursoCurrent : listCursos) {
            if (cursoCurrent.getNombreCurso().equals(cursos.getNombreCurso())) {
                cursoCurrent.setNombreCurso(cursos.getNombreCurso());
                cursoCurrent.setGuiaCatedra(cursos.getGuiaCatedra());
                change = true;
                break;
            }
        }
        if (change)
            conexion.saveData(listCursos);
    }

    @Override
    public void eliminar(Cursos cur) {
        List<Cursos> listCursos = conexion.getData(Cursos.class);
        boolean change = false;
        for (Cursos cursos : listCursos) {
            if (cursos.getNombreCurso().equals(cur.getNombreCurso())) {
                listCursos.remove(cursos);
                change = true;
                break;
            }
        }
        if (change)
            conexion.saveData(listCursos);
    }

}
