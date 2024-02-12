package com.campusland.utils.conexionpersistencia.conexionbdjson;

import com.campusland.repository.models.Departamentos;

public class ConexionBDJsonDepartamentos extends ConexionBDJsonBase<Departamentos> {
    
    private static ConexionBDJsonDepartamentos conexionDepartamentos;

    private ConexionBDJsonDepartamentos() {
        super("departamentos.json");
    }

    public static ConexionBDJsonDepartamentos getConexion() {
        if (conexionDepartamentos != null) {
            return conexionDepartamentos;
        } else {
            conexionDepartamentos = new ConexionBDJsonDepartamentos();
            return conexionDepartamentos;
        }
    }
}
