package com.campusland.repository.models;

import lombok.Data;

@Data
public class Ciudad {

    private int idCiudad;
    private String nombreCiudad;
    private static int nextCodigo;

    public Ciudad() {
        this.idCiudad = ++nextCodigo;
    }

    public Ciudad(int idCiudad, String nombreCiudad) {
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
    }

    public Ciudad(String nombreCiudad) {
        this.idCiudad = ++nextCodigo;
        this.nombreCiudad = nombreCiudad;
    }

    
}
