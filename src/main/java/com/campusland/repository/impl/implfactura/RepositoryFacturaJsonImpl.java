package com.campusland.repository.impl.implfactura;

import java.util.List;

import com.campusland.exceptiones.facturaexceptions.FacturaExceptionInsertDataBase;
import com.campusland.repository.RepositoryFactura;
import com.campusland.repository.models.Factura;
import com.campusland.utils.conexionpersistencia.conexionbdjson.ConexionBDJsonFacturas;

public class RepositoryFacturaJsonImpl implements RepositoryFactura {

    ConexionBDJsonFacturas conexion = ConexionBDJsonFacturas.getConexion();

    @Override
    public List<Factura> listar() {
        return conexion.getData(Factura.class);
    }

    @Override
    public void crear(Factura factura) throws FacturaExceptionInsertDataBase {
        List<Factura> listFacturas = conexion.getData(Factura.class);
        listFacturas.add(factura);
        conexion.saveData(listFacturas);
       
    }

}
