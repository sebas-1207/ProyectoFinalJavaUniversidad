package com.campusland.utils.conexionpersistencia.conexionbdjson;

import com.campusland.repository.models.Profesores;

public class ConexionBDJsonProfesor extends ConexionBDJsonBase<Profesores> {

    private static ConexionBDJsonProfesor conexionProfesores;

    private ConexionBDJsonProfesor(){
        super("profesores.json");
    }

    public static ConexionBDJsonProfesor getConexion(){
        if (conexionProfesores != null){
            return conexionProfesores;
        }else{
            conexionProfesores = new ConexionBDJsonProfesor();
            return conexionProfesores;
        }
    }
    
}
