package com.campusland.repository.models;

import lombok.Data;

@Data
public class Asignaturas {

    private int idAsignatura;
    private String nombreAsignatura;
    private int cantidadCreditos;
    private int programaId;
    private int periodoId;
    private int cursoId;
    private int profesorId;
    private static int nextCodigo;

    public Asignaturas() {
        this.idAsignatura = ++nextCodigo;
    }

    public Asignaturas(int idAsignatura, String nombreAsignatura, int cantidadCreditos, int programaId, int periodoId,
            int cursoId, int profesorId) {
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.cantidadCreditos = cantidadCreditos;
        this.programaId = programaId;
        this.periodoId = periodoId;
        this.cursoId = cursoId;
        this.profesorId = profesorId;
    }

    public Asignaturas(String nombreAsignatura, int cantidadCreditos, int programaId, int periodoId,
            int cursoId, int profesorId) {
        this.idAsignatura = ++nextCodigo;
        this.nombreAsignatura = nombreAsignatura;
        this.cantidadCreditos = cantidadCreditos;
        this.programaId = programaId;
        this.periodoId = periodoId;
        this.cursoId = cursoId;
        this.profesorId = profesorId;
    }

    public void imprimir(){
        System.out.println("Nombre de la Asignatura: "+this.getNombreAsignatura());
        System.out.println("Cantidad de Creditos: " + this.getCantidadCreditos());    
    }

}
