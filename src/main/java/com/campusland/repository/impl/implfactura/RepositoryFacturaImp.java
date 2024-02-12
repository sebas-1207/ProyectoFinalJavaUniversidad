package com.campusland.repository.impl.implfactura;

import java.util.List;

import com.campusland.repository.RepositoryFactura;
import com.campusland.repository.models.Factura;
import com.campusland.utils.conexionpersistencia.conexiondblist.ConexionBDList;

public class RepositoryFacturaImp implements RepositoryFactura {

    ConexionBDList conexion = ConexionBDList.getConexion();

    @Override
    public List<Factura> listar() {
        return conexion.getListFacturas();
        
    }

    @Override
    public void crear(Factura factura) {
       conexion.getListFacturas().add(factura);
        
    }

}
