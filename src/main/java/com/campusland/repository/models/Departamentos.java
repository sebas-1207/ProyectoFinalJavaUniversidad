package com.campusland.repository.models;

import lombok.Data;

@Data
public class Departamentos {
    

    private int idDepartamento;
    private String nombreDepartamento;
    private static int nextCodigo;

    public Departamentos(){
        this.idDepartamento = ++nextCodigo;
    }

    public Departamentos(int idDepartamento, String nombreDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }

    public Departamentos(String nombreDepartamento) {
        this.idDepartamento = ++nextCodigo;
        this.nombreDepartamento = nombreDepartamento;
    }

    public void imprimir(){
        System.out.println("Nombre: "+this.getNombreDepartamento());   
    }

}
