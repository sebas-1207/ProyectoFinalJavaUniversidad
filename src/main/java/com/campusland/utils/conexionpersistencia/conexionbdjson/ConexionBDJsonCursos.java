package com.campusland.utils.conexionpersistencia.conexionbdjson;

import com.campusland.repository.models.Cursos;

public class ConexionBDJsonCursos extends ConexionBDJsonBase<Cursos>{

    private static ConexionBDJsonCursos conexionBDJsonCursos;

    private ConexionBDJsonCursos(){
        super("cursos.json");
    }

    public static ConexionBDJsonCursos getConexion() {
        if (conexionBDJsonCursos != null) {
            return conexionBDJsonCursos;
        } else {
            conexionBDJsonCursos = new ConexionBDJsonCursos();
            return conexionBDJsonCursos;
        }
    }
}
