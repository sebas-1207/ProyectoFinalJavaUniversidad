package com.campusland.repository.models;

import java.sql.Date;

import lombok.Data;

@Data
public class Personas {

    private int idPersona;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombre;
    private String apellido;
    private String numeroTelefono;
    private Date fechaNacimiento;
    private String sexo;
    private int direccionNumero;
    private int ciudadId;
    private static int nextCodigo;

    public Personas() {
        this.idPersona = ++nextCodigo;
    }

    

    public Personas(int idPersona, String tipoDocumento, String numeroDocumento, String nombre, String apellido,
            String numeroTelefono, Date fechaNacimiento, String sexo, int direccionNumero, int ciudadId) {
        this.idPersona = idPersona;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.direccionNumero = direccionNumero;
        this.ciudadId = ciudadId;
    }



    public Personas(String tipoDocumento, String numeroDocumento, String nombre, String apellido,
    String numeroTelefono, Date fechaNacimiento, String sexo, int direccionNumero, int ciudadId) {
        this.idPersona = ++nextCodigo;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.direccionNumero = direccionNumero;
        this.ciudadId = ciudadId;
    }

}
