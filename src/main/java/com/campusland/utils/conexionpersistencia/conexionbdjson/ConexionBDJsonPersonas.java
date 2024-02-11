package com.campusland.utils.conexionpersistencia.conexionbdjson;

import com.campusland.respository.models.Personas;

public class ConexionBDJsonPersonas extends ConexionBDJsonBase<Personas>{
    
    private static ConexionBDJsonPersonas conexionPersonas;

    private ConexionBDJsonPersonas(){
        super("personas.json");
    }

    public static ConexionBDJsonPersonas getConexion(){
        if(conexionPersonas != null){
            return conexionPersonas;
        }else{
            conexionPersonas = new ConexionBDJsonPersonas();
            return conexionPersonas;
        }
    }
}
