package com.campusland.repository.models;

import lombok.Data;

@Data
public class Programas {
    
    private int idPrograma;
    private String nombrePrograma;
    private String Nivel;
    private static int nextCodigo;

    public Programas(){
        this.idPrograma = ++nextCodigo;
    }

    public Programas(int idPrograma, String nombrePrograma, String nivel) {
        this.idPrograma = idPrograma;
        this.nombrePrograma = nombrePrograma;
        Nivel = nivel;
    }

    public Programas(String nombrePrograma, String nivel) {
        this.idPrograma = ++nextCodigo;
        this.nombrePrograma = nombrePrograma;
        Nivel = nivel;
    }

    public void imprimir(){
        System.out.println("Nombre: " + this.getNombrePrograma());
        System.out.println("Nivel: " + this.getNivel());
    }
    
}
