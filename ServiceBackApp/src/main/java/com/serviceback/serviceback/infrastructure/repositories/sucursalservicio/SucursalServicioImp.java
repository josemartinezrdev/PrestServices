package com.serviceback.serviceback.infrastructure.repositories.sucursalservicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.ISucursalServicio;
import com.serviceback.serviceback.domain.entities.SucursalServicio;
import com.serviceback.serviceback.domain.entities.fkClass.SucursalServicioPK;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class SucursalServicioImp implements ISucursalServicio {

    @Autowired
    private SucursalServicioRepository sucursalservicioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SucursalServicio> findAll() {
        List<SucursalServicio> sucursalservicios = sucursalservicioRepository.findAll();
        if (sucursalservicios.isEmpty()) {
            throw new GlobalExceptions("No se encontraron campos.");
        }
        return sucursalservicios;
    }

    @Transactional(readOnly = true)
    @Override
    public SucursalServicio findById(SucursalServicioPK id) throws GlobalExceptions {
        return sucursalservicioRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas buscar no existe"));
    }

    @Transactional
    @Override
    public SucursalServicio save(SucursalServicio sucursalservicio) throws GlobalExceptions {
        if (sucursalservicio == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return sucursalservicioRepository.save(sucursalservicio);
    }

    @Transactional
    @Override
    public SucursalServicio update(SucursalServicioPK id, SucursalServicio newSucursalServicio)
            throws GlobalExceptions {
        SucursalServicio sucursalservicio = sucursalservicioRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas actualizar no existe"));
        sucursalservicio.setServicio(newSucursalServicio.getServicio());
        sucursalservicio.setSucursal(newSucursalServicio.getSucursal());
        sucursalservicio.setValorservicio(newSucursalServicio.getValorservicio());
        return sucursalservicioRepository.save(sucursalservicio);
    }

    @Transactional
    @Override
    public SucursalServicio delete(SucursalServicioPK id) throws GlobalExceptions {
        SucursalServicio sucursalservicio = sucursalservicioRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas eliminar no existe"));
        sucursalservicioRepository.delete(sucursalservicio);
        return sucursalservicio;
    }
}
