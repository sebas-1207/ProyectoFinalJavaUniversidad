package com.campusland.respository.models;

import lombok.Data;

@Data
public class Profesores{
    
    private int idProfesor;
    private String especialidad;
    private int personaId;
    private int departamentoId;
    private static int nextCodigo;

    public Profesores(){
        this.idProfesor = ++nextCodigo;
    }

    public Profesores(int idProfesor, String especialidad, int personaId, int departamentoId) {
        this.idProfesor = idProfesor;
        this.especialidad = especialidad;
        this.personaId = personaId;
        this.departamentoId = departamentoId;
    }
    public Profesores(String especialidad, int personaId, int departamentoId) {
        this.idProfesor = ++nextCodigo;
        this.especialidad = especialidad;
        this.personaId = personaId;
        this.departamentoId = departamentoId;
    }

    
    
}
