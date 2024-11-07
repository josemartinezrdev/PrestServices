package com.serviceback.serviceback.infrastructure.repositories.ordenservicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IOrdenServicio;
import com.serviceback.serviceback.domain.entities.OrdenServicio;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class OrdenServicioImp implements IOrdenServicio {

    @Autowired
    private OrdenServicioRepository ordenservicioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<OrdenServicio> findAll() {
        List<OrdenServicio> ordenservicios = ordenservicioRepository.findAll();
        if (ordenservicios.isEmpty()) {
            throw new GlobalExceptions("No se encontraron valores.");
        }
        return ordenservicios;
    }

    @Transactional(readOnly = true)
    @Override
    public OrdenServicio findById(int nrodoc) throws GlobalExceptions {
        return ordenservicioRepository.findById(nrodoc)
                .orElseThrow(() -> new GlobalExceptions("La Valor que intenta buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public OrdenServicio save(OrdenServicio ordenservicio) throws GlobalExceptions {
        if (ordenservicio == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return ordenservicioRepository.save(ordenservicio);
    }

    @Transactional
    @Override
    public OrdenServicio update(int nrodoc, OrdenServicio newOrdenServicio) throws GlobalExceptions {
        OrdenServicio ordenservicio = ordenservicioRepository.findById(nrodoc)
                .orElseThrow(() -> new GlobalExceptions("El valor que intenta actualizar no existe"));
        ordenservicio.setCliente(newOrdenServicio.getCliente());
        ordenservicio.setEmpleado(newOrdenServicio.getEmpleado());
        ordenservicio.setEstadoOrden(newOrdenServicio.getEstadoOrden());
        ordenservicio.setMotivo(newOrdenServicio.getMotivo());
        return ordenservicioRepository.save(ordenservicio);
    }

    @Transactional
    @Override
    public OrdenServicio delete(int nrodoc) throws GlobalExceptions {
        OrdenServicio ordenservicio = ordenservicioRepository.findById(nrodoc)
                .orElseThrow(() -> new GlobalExceptions("El valor que intentas eliminar no existe"));
        ordenservicioRepository.delete(ordenservicio);
        return ordenservicio;
    }

    @Override
    public List<OrdenServicio> findByEstadoOrden_Id(Integer estadoOrdenId) {
        return ordenservicioRepository.findByEstadoOrden_Id(estadoOrdenId);
    }
}
