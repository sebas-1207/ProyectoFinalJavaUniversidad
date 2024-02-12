package com.campusland.repository.models;

import lombok.Data;

@Data
public class Cursos {

    private int idCurso;
    private String nombreCurso;
    private String guiaCatedra;
    private static int nextCodigo;

    public Cursos() {
        this.idCurso = ++nextCodigo;
    }

    public Cursos(int idCurso, String nombreCurso, String guiaCatedra) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.guiaCatedra = guiaCatedra;
    }

    public Cursos(String nombreCurso, String guiaCatedra) {
        this.idCurso = ++nextCodigo;
        this.nombreCurso = nombreCurso;
        this.guiaCatedra = guiaCatedra;
    }

    public void imprimir() {
        System.out.println("Nombre: " + this.getNombreCurso());
        System.out.println("Guia Catedra: " + this.getGuiaCatedra());
    }

}
