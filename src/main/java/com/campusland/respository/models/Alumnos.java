package com.campusland.respository.models;

import lombok.Data;

@Data
public class Alumnos {
    
    private int idAlumno;
    private int personaId;
    private static int nextCodigo;


    public Alumnos(){
        this.idAlumno = ++nextCodigo;
    }


    public Alumnos(int idAlumno, int personaId) {
        this.idAlumno = idAlumno;
        this.personaId = personaId;
    }

    public Alumnos(int personaId) {
        this.idAlumno = ++nextCodigo;
        this.personaId = personaId;
    }

    public void imprimir(){
        System.out.println("Documento: "+this.getPersonaId());    
    }

    
}
