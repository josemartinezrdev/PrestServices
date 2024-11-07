package com.serviceback.serviceback.infrastructure.repositories.estadoorden;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IEstadoOrden;
import com.serviceback.serviceback.domain.entities.EstadoOrden;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class EstadoOrdenImpl implements IEstadoOrden {

    @Autowired
    private EstadoOrdenRepository estadoordenRepository;

    @Transactional(readOnly = true)
    @Override
    public List<EstadoOrden> findAll() throws GlobalExceptions {
        List<EstadoOrden> estadoordenes = (List<EstadoOrden>) estadoordenRepository.findAll();
        if (estadoordenes.isEmpty()) {
            throw new GlobalExceptions("No se encontraron estadoordenes.");
        }
        return estadoordenes;
    }

    @Transactional(readOnly = true)
    @Override
    public EstadoOrden findById(int id) throws GlobalExceptions {
        return estadoordenRepository.findById(id).orElseThrow(() -> new GlobalExceptions("El EstadoOrden no existe"));

    }

    @Transactional
    @Override
    public EstadoOrden save(EstadoOrden estadoorden) throws GlobalExceptions {
        if (estadoorden == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        if (estadoordenRepository.existsByNombre(estadoorden.getNombre())) {
            throw new GlobalExceptions("El valor ya existe!");
        }

        return estadoordenRepository.save(estadoorden);
    }

    @Transactional
    @Override
    public EstadoOrden update(int id, EstadoOrden estadoorden) throws GlobalExceptions {
        EstadoOrden newEstadoOrden = estadoordenRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El EstadoOrden que intentas actualizar no existe"));
        newEstadoOrden.setNombre(estadoorden.getNombre());
        return estadoordenRepository.save(newEstadoOrden);
    }

    @Transactional
    @Override
    public EstadoOrden delete(int id) throws GlobalExceptions {
        EstadoOrden estadoorden = estadoordenRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El estadoorden que intentas eliminar no existe"));
        estadoordenRepository.delete(estadoorden);
        return estadoorden;
    }

}
