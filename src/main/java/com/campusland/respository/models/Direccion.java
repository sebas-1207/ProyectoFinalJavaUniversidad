package com.campusland.respository.models;

import lombok.Data;

@Data
public class Direccion {

    private int numeroDireccion;
    private String tipoDireccion;
    private int numero;
    private static int nextCodigo;

    public Direccion() {
        this.numeroDireccion = ++nextCodigo;
    }

    

    public Direccion(int numeroDireccion, String tipoDireccion, int numero) {
        this.numeroDireccion = numeroDireccion;
        this.tipoDireccion = tipoDireccion;
        this.numero = numero;
    }



    public Direccion(String tipoDireccion, int numero) {
        this.numeroDireccion = ++nextCodigo;
        this.tipoDireccion = tipoDireccion;
        this.numero = numero;
    }

}
