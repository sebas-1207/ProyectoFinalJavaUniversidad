package com.campusland.repository.models;

import lombok.Data;

@Data
public class Tarifas {

    private int idTarifas;
    private Double costoCreditos;
    private Double valorCredito;
    private int programaId;
    private int periodoId;
    private static int nextCodigo;

    public Tarifas() {
        this.idTarifas = ++nextCodigo;
    }

    public Tarifas(int idTarifas, Double costoCreditos, Double valorCredito, int programaId, int periodoId) {
        this.idTarifas = idTarifas;
        this.costoCreditos = costoCreditos;
        this.valorCredito = valorCredito;
        this.programaId = programaId;
        this.periodoId = periodoId;
    }

    public Tarifas(Double costoCreditos, Double valorCredito, int programaId, int periodoId) {
        this.idTarifas = ++nextCodigo;
        this.costoCreditos = costoCreditos;
        this.valorCredito = valorCredito;
        this.programaId = programaId;
        this.periodoId = periodoId;
    }

    public void imprimir() {
        System.out.println("Costo de los creditos: " + this.getCostoCreditos());
        System.out.println("Valor del credito: " + this.getValorCredito());
    }
}
