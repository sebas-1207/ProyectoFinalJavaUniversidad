package com.campusland.repository.models;

import lombok.Data;

@Data
public class Matriculas {

    private int idMatricula;
    private int alumnoId;
    private int asignaturaId;
    private static int nextCodigo;

    public Matriculas() {
        this.idMatricula = ++nextCodigo;
    }

    public Matriculas(int idMatricula, int alumnoId, int asignaturaId) {
        this.idMatricula = idMatricula;
        this.alumnoId = alumnoId;
        this.asignaturaId = asignaturaId;
    }

    public Matriculas(int alumnoId, int asignaturaId) {
        this.idMatricula = ++nextCodigo;
        this.alumnoId = alumnoId;
        this.asignaturaId = asignaturaId;
    }

    public void imprimir() {
        System.out.println("");
    }
}
