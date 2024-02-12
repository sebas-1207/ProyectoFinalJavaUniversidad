package com.campusland.repository.models;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class Programacion {

    private int idHorario;
    private Date dia;
    private Time horaInicio;
    private Time horaFin;
    private int asignaturaId;
    private int matriculaId;
    private int salonId;
    private static int nextCodigo;

    public Programacion() {
        this.idHorario = ++nextCodigo;
    }

    public Programacion(int idHorario, Date dia, Time horaInicio, Time horaFin, int asignaturaId, int matriculaId,
            int salonId) {
        this.idHorario = idHorario;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.asignaturaId = asignaturaId;
        this.matriculaId = matriculaId;
        this.salonId = salonId;
    }

    public Programacion(Date dia, Time horaInicio, Time horaFin, int asignaturaId, int matriculaId,
            int salonId) {
        this.idHorario = ++nextCodigo;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.asignaturaId = asignaturaId;
        this.matriculaId = matriculaId;
        this.salonId = salonId;
    }

    public void imprimir() {
        System.out.println("Dia: " + this.getDia());
        System.out.println("Hora de Inicio: " + this.getHoraInicio());
        System.out.println("Hora de Fin: " + this.getHoraFin());
    }
}
