package com.serviceback.serviceback.infrastructure.repositories.aprobacionservicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IAprobacionServicio;
import com.serviceback.serviceback.domain.entities.AprobacionServicio;
import com.serviceback.serviceback.domain.entities.Servicio;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class AprobacionServicioImp implements IAprobacionServicio {

    @Autowired
    private AprobacionServicioRepository aprobacionservicioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AprobacionServicio> findByCliente(String nrodoc, int idestado) {
        List<AprobacionServicio> aprobacionservicios = aprobacionservicioRepository.findByCliente(nrodoc, idestado);
        if (aprobacionservicios.isEmpty()) {
            throw new GlobalExceptions("No se encontraron valores.");
        }
        return aprobacionservicios;
    }

    @Transactional(readOnly = true)
    @Override
    public List<AprobacionServicio> findAll() {
        List<AprobacionServicio> aprobacionservicios = aprobacionservicioRepository.findAll();
        if (aprobacionservicios.isEmpty()) {
            throw new GlobalExceptions("No se encontraron valores.");
        }
        return aprobacionservicios;
    }

    @Transactional(readOnly = true)
    @Override
    public AprobacionServicio findById(int nrodoc) throws GlobalExceptions {
        return aprobacionservicioRepository.findById(nrodoc)
                .orElseThrow(() -> new GlobalExceptions("La Valor que intenta buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public AprobacionServicio save(AprobacionServicio aprobacionservicio) throws GlobalExceptions {
        if (aprobacionservicio == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return aprobacionservicioRepository.save(aprobacionservicio);
    }

    @Transactional
    @Override
    public AprobacionServicio update(int nrodoc, AprobacionServicio newAprobacionServicio) throws GlobalExceptions {
        AprobacionServicio aprobacionservicio = aprobacionservicioRepository.findById(nrodoc)
                .orElseThrow(() -> new GlobalExceptions("El valor que intenta actualizar no existe"));

        aprobacionservicio.setHallazgo(newAprobacionServicio.getHallazgo());
        aprobacionservicio.setSolucion(newAprobacionServicio.getSolucion());
        aprobacionservicio.setCliente(newAprobacionServicio.getCliente());
        aprobacionservicio.setOrdenservicio(newAprobacionServicio.getOrdenservicio());
        aprobacionservicio.setEstadoaprobacion(newAprobacionServicio.getEstadoaprobacion());
        aprobacionservicio.setServicio(newAprobacionServicio.getServicio());
        return aprobacionservicioRepository.save(aprobacionservicio);
    }

    @Transactional
    @Override
    public AprobacionServicio delete(int nrodoc) throws GlobalExceptions {
        AprobacionServicio aprobacionservicio = aprobacionservicioRepository.findById(nrodoc)
                .orElseThrow(() -> new GlobalExceptions("El valor que intentas eliminar no existe"));
        aprobacionservicioRepository.delete(aprobacionservicio);
        return aprobacionservicio;
    }

    @Override
    public Servicio findAprobacionesWithServiceByNorden(int norden) {
        return aprobacionservicioRepository.findAprobacionesWithServiceByNorden(norden);
    }
}
