package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.facturaexceptions.FacturaExceptionInsertDataBase;
import com.campusland.repository.models.Factura;

public interface ServiceFactura {

    List<Factura> listar();

    void crear(Factura factura)throws FacturaExceptionInsertDataBase;

    
}
