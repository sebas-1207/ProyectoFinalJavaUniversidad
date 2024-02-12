package com.campusland.repository.models;

import java.time.Year;

import lombok.Data;

@Data
public class Periodo {

    private int idPeriodo;
    private int codigoEspecifico;
    private Year año;
    private int semestre;
    private static int nextCodigo;

    public Periodo() {
        this.idPeriodo = ++nextCodigo;
    }

    public Periodo(int idPeriodo, int codigoEspecifico, Year año, int semestre) {
        this.idPeriodo = idPeriodo;
        this.codigoEspecifico = codigoEspecifico;
        this.año = año;
        this.semestre = semestre;
    }

    public Periodo(int codigoEspecifico, Year año, int semestre) {
        this.idPeriodo = ++nextCodigo;
        this.codigoEspecifico = codigoEspecifico;
        this.año = año;
        this.semestre = semestre;
    }

    public void imprimir() {
        System.out.println("Codigo Especifico: " + this.getCodigoEspecifico());
        System.out.println("Año: " + this.getAño());
        System.out.println("Semestre: " + this.getSemestre());
    }

}
