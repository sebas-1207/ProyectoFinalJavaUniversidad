package com.campusland.repository.models;

import lombok.Data;

@Data
public class Salones {

    private int idSalones;
    private int capacidadAlumnos;
    private int numeroIdentificacion;
    private int edificioId;
    private static int nextCodigo;

    public Salones() {
        this.idSalones = ++nextCodigo;
    }

    public Salones(int idSalones, int capacidadAlumnos, int numeroIdentificacion, int edificioId) {
        this.idSalones = idSalones;
        this.capacidadAlumnos = capacidadAlumnos;
        this.numeroIdentificacion = numeroIdentificacion;
        this.edificioId = edificioId;
    }

    public Salones(int capacidadAlumnos, int numeroIdentificacion, int edificioId) {
        this.idSalones = ++nextCodigo;
        this.capacidadAlumnos = capacidadAlumnos;
        this.numeroIdentificacion = numeroIdentificacion;
        this.edificioId = edificioId;
    }

    public void imprimir() {
        System.out.println("Capacidad de Alumnos" + this.getCapacidadAlumnos());
        System.out.println("Numero de Identificacion del Salon: " + this.getNumeroIdentificacion());
    }
}
