package com.serviceback.serviceback.infrastructure.repositories.estadoarprobacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IEstadoAprobacion;
import com.serviceback.serviceback.domain.entities.EstadoAprobacion;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class EstadoAprobacionImpl implements IEstadoAprobacion {

    @Autowired
    private EstadoAprobacionRepository estadoaprobacionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<EstadoAprobacion> findAll() throws GlobalExceptions {
        List<EstadoAprobacion> estadoaprobaciones = (List<EstadoAprobacion>) estadoaprobacionRepository.findAll();
        if (estadoaprobaciones.isEmpty()) {
            throw new GlobalExceptions("No se encontraron estadoaprobaciones.");
        }
        return estadoaprobaciones;
    }

    @Transactional(readOnly = true)
    @Override
    public EstadoAprobacion findById(int id) throws GlobalExceptions {
        return estadoaprobacionRepository.findById(id).orElseThrow(() -> new GlobalExceptions("El EstadoAprobacion no existe"));

    }

    @Transactional
    @Override
    public EstadoAprobacion save(EstadoAprobacion estadoaprobacion) throws GlobalExceptions {
        if (estadoaprobacion == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        if (estadoaprobacionRepository.existsByNombre(estadoaprobacion.getNombre())) {
            throw new GlobalExceptions("El valor ya existe!");
        }

        return estadoaprobacionRepository.save(estadoaprobacion);
    }

    @Transactional
    @Override
    public EstadoAprobacion update(int id, EstadoAprobacion estadoaprobacion) throws GlobalExceptions {
        EstadoAprobacion newEstadoAprobacion = estadoaprobacionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El EstadoAprobacion que intentas actualizar no existe"));
        newEstadoAprobacion.setNombre(estadoaprobacion.getNombre());
        return estadoaprobacionRepository.save(newEstadoAprobacion);
    }

    @Transactional
    @Override
    public EstadoAprobacion delete(int id) throws GlobalExceptions {
        EstadoAprobacion estadoaprobacion = estadoaprobacionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El estadoaprobacion que intentas eliminar no existe"));
        estadoaprobacionRepository.delete(estadoaprobacion);
        return estadoaprobacion;
    }

}
