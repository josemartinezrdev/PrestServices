package com.serviceback.serviceback.infrastructure.repositories.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IServicio;
import com.serviceback.serviceback.domain.entities.Servicio;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class ServicioImp implements IServicio {
    @Autowired
    private ServicioRepository servicioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Servicio> findAll() {
        List<Servicio> servicios = servicioRepository.findAll();
        if (servicios.isEmpty()) {
            throw new GlobalExceptions("No se encontraron servicios.");
        }
        return servicios;
    }

    @Transactional(readOnly = true)
    @Override
    public Servicio findById(int id) throws GlobalExceptions {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El servicio que intentas buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Servicio save(Servicio servicio) throws GlobalExceptions {
        if (servicio == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        if (servicioRepository.existsByNombre(servicio.getNombre())) {
            throw new GlobalExceptions("El valor ya existe");
        }

        return servicioRepository.save(servicio);
    }

    @Transactional
    @Override
    public Servicio update(int id, Servicio newServicio) throws GlobalExceptions {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El servicio que intentas actualizar no existe"));
        servicio.setNombre(newServicio.getNombre());
        servicio.setDescripcion(newServicio.getDescripcion());
        servicio.setValorservicio(newServicio.getValorservicio());
        servicio.setRequiereinsumo(newServicio.getRequiereinsumo());
        return servicioRepository.save(servicio);
    }

    @Transactional
    @Override
    public Servicio delete(int id) throws GlobalExceptions {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El servicio que intentas eliminar no existe"));
        servicioRepository.delete(servicio);
        return servicio;
    }
}
