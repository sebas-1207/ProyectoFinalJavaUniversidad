package com.campusland.utils.conexionpersistencia.conexionbdjson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.campusland.repository.models.Cursos;
import com.campusland.repository.models.Departamentos;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Data;

@Data
public class ConexionBDJson  {

    private static ConexionBDJson conexion;
    private List<Departamentos> listaDepartamentos;
    private List<Cursos> listaCursos;

    private ConexionBDJson() {
        listaDepartamentos = new ArrayList<>();
        listaCursos = new ArrayList<>();
       
    }

    public static ConexionBDJson getConexion() {
        if (conexion != null) {
            return conexion;
        } else {
            conexion = new ConexionBDJson();
            return conexion;
        }
    }

    public List<Departamentos> getDataDepartamentos() {
        ObjectMapper objectMapper=new ObjectMapper();            
            try{
                listaDepartamentos=objectMapper.readValue(new File("departamentos.json"), new TypeReference<List<Departamentos>>(){});
            }catch(IOException e){
               e.printStackTrace();
            }
            return listaDepartamentos;
    }
    public void saveDataDepartamentos(List<Departamentos> listDepartamentosUpdate){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File("departamentos.json"), listDepartamentosUpdate);
            System.out.println("Se guardo los departamentos en departamentos.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Cursos> getDataCursos() {
        ObjectMapper objectMapper=new ObjectMapper();            
            try{
                listaCursos=objectMapper.readValue(new File("cursos.json"), new TypeReference<List<Cursos>>(){});
            }catch(IOException e){
               e.printStackTrace();
            }
            return listaCursos;
    }
    public void saveDataCursos(List<Cursos> listCursosUpdate){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File("cursos.json"), listCursosUpdate);
            System.out.println("Se guardo los cursos en cursos.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    

    

}
