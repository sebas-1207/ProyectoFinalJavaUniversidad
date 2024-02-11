package com.campusland.utils.conexionpersistencia.conexionbdjson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.campusland.respository.models.Cliente;
import com.campusland.respository.models.Cursos;
import com.campusland.respository.models.Departamentos;
import com.campusland.respository.models.Factura;
import com.campusland.respository.models.Producto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Data;

@Data
public class ConexionBDJson  {

    private static ConexionBDJson conexion;
    private List<Departamentos> listaDepartamentos;
    private List<Cursos> listaCursos;
    private List<Cliente> listaClientes;
    private List<Producto> listaProductos;
    private List<Factura> listFacturas;

    private ConexionBDJson() {
        listaClientes = new ArrayList<>();
        listaProductos = new ArrayList<>();
        listFacturas = new ArrayList<>();
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

    

    public List<Cliente> getDataClientes() {
        ObjectMapper objectMapper=new ObjectMapper();            
            try{
                listaClientes=objectMapper.readValue(new File("clientes.json"), new TypeReference<List<Cliente>>(){});
            }catch(IOException e){
               e.printStackTrace();
            }
            return listaClientes;
    }
    public void saveDataClientes(List<Cliente> listClientesUpdate){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File("clientes.json"), listClientesUpdate);
            System.out.println("Se guardo los clientes en cliente.json");
        } catch (IOException e) {
            e.printStackTrace();
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
