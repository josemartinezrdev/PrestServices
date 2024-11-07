package com.serviceback.serviceback.infrastructure.repositories.estadoserorden;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IEstadoSerOrden;
import com.serviceback.serviceback.domain.entities.EstadoSerOrden;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class EstadoSerOrdenImpl implements IEstadoSerOrden {

    @Autowired
    private EstadoSerOrdenRepository estadoserordenRepository;

    @Transactional(readOnly = true)
    @Override
    public List<EstadoSerOrden> findAll() throws GlobalExceptions {
        List<EstadoSerOrden> estadoserordenes = (List<EstadoSerOrden>) estadoserordenRepository.findAll();
        if (estadoserordenes.isEmpty()) {
            throw new GlobalExceptions("No se encontraron estadoserordenes.");
        }
        return estadoserordenes;
    }

    @Transactional(readOnly = true)
    @Override
    public EstadoSerOrden findById(int id) throws GlobalExceptions {
        return estadoserordenRepository.findById(id).orElseThrow(() -> new GlobalExceptions("El EstadoSerOrden no existe"));

    }

    @Transactional
    @Override
    public EstadoSerOrden save(EstadoSerOrden estadoserorden) throws GlobalExceptions {
        if (estadoserorden == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        if (estadoserordenRepository.existsByNombre(estadoserorden.getNombre())) {
            throw new GlobalExceptions("El valor ya existe!");
        }

        return estadoserordenRepository.save(estadoserorden);
    }

    @Transactional
    @Override
    public EstadoSerOrden update(int id, EstadoSerOrden estadoserorden) throws GlobalExceptions {
        EstadoSerOrden newEstadoSerOrden = estadoserordenRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El EstadoSerOrden que intentas actualizar no existe"));
        newEstadoSerOrden.setNombre(estadoserorden.getNombre());
        return estadoserordenRepository.save(newEstadoSerOrden);
    }

    @Transactional
    @Override
    public EstadoSerOrden delete(int id) throws GlobalExceptions {
        EstadoSerOrden estadoserorden = estadoserordenRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El estadoserorden que intentas eliminar no existe"));
        estadoserordenRepository.delete(estadoserorden);
        return estadoserorden;
    }

}
