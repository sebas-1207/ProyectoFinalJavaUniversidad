package com.campusland.repository.models;

import com.campusland.repository.enums.TipoDireccion;

import lombok.Data;

@Data
public class Direccion {

    private int numeroDireccion;
    private TipoDireccion tipoDireccion;
    private int numero;
    private String barrio;
    private static int nextCodigo;

    public Direccion() {
        this.numeroDireccion = ++nextCodigo;
    }

    

    public Direccion(int numeroDireccion, TipoDireccion tipoDireccion, int numero, String barrio) {
        this.numeroDireccion = numeroDireccion;
        this.tipoDireccion = tipoDireccion;
        this.numero = numero;
        this.barrio = barrio;
    }



    public Direccion(TipoDireccion tipoDireccion, int numero, String barrio) {
        this.numeroDireccion = ++nextCodigo;
        this.tipoDireccion = tipoDireccion;
        this.numero = numero;
        this.barrio = barrio;
    }

}
