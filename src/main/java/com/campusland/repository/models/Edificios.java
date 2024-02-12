package com.campusland.repository.models;

import lombok.Data;

@Data
public class Edificios {

    private int idEdificios;
    private int numeroEdificio;
    private int numeroPiso;
    private int capacidadMax_Salones;
    private static int nextCodigo;

    public Edificios(){
        this.idEdificios = ++nextCodigo;
    }

    public Edificios(int idEdificios, int numeroEdificio, int numeroPiso, int capacidadMax_Salones) {
        this.idEdificios = idEdificios;
        this.numeroEdificio = numeroEdificio;
        this.numeroPiso = numeroPiso;
        this.capacidadMax_Salones = capacidadMax_Salones;
    }

    public Edificios(int numeroEdificio, int numeroPiso, int capacidadMax_Salones) {
        this.idEdificios = ++nextCodigo;
        this.numeroEdificio = numeroEdificio;
        this.numeroPiso = numeroPiso;
        this.capacidadMax_Salones = capacidadMax_Salones;
    }

    public void imprimir(){
        System.out.println("Numero de Edificio: "+this.getNumeroEdificio());
        System.out.println("Numero de Piso: " + this.getNumeroPiso());
        System.out.println("Capacidad maxima de salones: " + this.getCapacidadMax_Salones());    
    }
}
