package com.campusland.repository.models;

import lombok.Data;

@Data
public class Alumnos{
    
    private int idAlumno;
    private int personaId;
    private int programaId;
    private static int nextCodigo;


    public Alumnos(){
        this.idAlumno = ++nextCodigo;
    }


    public Alumnos(int idAlumno, int personaId, int programaId) {
        this.idAlumno = idAlumno;
        this.personaId = personaId;
        this.programaId = programaId;
    }

    public Alumnos(int personaId, int programaId) {
        this.idAlumno = ++nextCodigo;
        this.personaId = personaId;
        this.programaId = programaId;
    }
    
    public void imrpimir(){
        System.out.println("Documento" + this.getPersonaId());
    }

    public void imprimir(){
        System.out.println("Numero de Documento: " + this.getPersonaId());
        System.out.println("Nombre: " + this.getPersonaId());
    }
}
