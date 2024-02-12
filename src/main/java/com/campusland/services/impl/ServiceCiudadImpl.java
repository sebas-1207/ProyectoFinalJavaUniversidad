package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.ciudadexceptions.CiudadNullException;
import com.campusland.repository.RepositoryCiudad;
import com.campusland.repository.models.Ciudad;
import com.campusland.services.ServiceCiudad;

public class ServiceCiudadImpl implements ServiceCiudad {
    
    private final RepositoryCiudad crudRepositoryCiudad;

    public ServiceCiudadImpl(RepositoryCiudad crudRepositoryCiudad){
        this.crudRepositoryCiudad = crudRepositoryCiudad;
    }

    @Override
    public List<Ciudad> listar() {
        return this.crudRepositoryCiudad.listar();
    }

    @Override
    public Ciudad porNombre(String nombreCiudad) throws CiudadNullException {
        Ciudad ciudad = this.crudRepositoryCiudad.porNombre(nombreCiudad);
        if (ciudad != null){
            return ciudad;
        }else{
            throw new CiudadNullException("No se encontro la ciudad por el nombre");
        }
    }

    @Override
    public void crear(Ciudad ciudad) {
        this.crudRepositoryCiudad.crear(ciudad);
    }

    @Override
    public void editar(Ciudad ciudad) {
        this.crudRepositoryCiudad.editar(ciudad);
    }
}
