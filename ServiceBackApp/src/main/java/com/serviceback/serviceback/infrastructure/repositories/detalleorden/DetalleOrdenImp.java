package com.serviceback.serviceback.infrastructure.repositories.detalleorden;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IDetalleOrden;
import com.serviceback.serviceback.domain.entities.DetalleOrden;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class DetalleOrdenImp implements IDetalleOrden {
    @Autowired
    private DetalleOrdenRepository detalleordenRepository;

    @Transactional(readOnly = true)
    @Override
    public List<DetalleOrden> findAll() {
        List<DetalleOrden> detalleordens = detalleordenRepository.findAll();
        if (detalleordens.isEmpty()) {
            throw new GlobalExceptions("No se encontraron detalles de ordenes.");
        }
        return detalleordens;
    }

    @Transactional(readOnly = true)
    @Override
    public DetalleOrden findById(int id) throws GlobalExceptions {
        return detalleordenRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El detalle de la orden que intenta buscar no existe"));
    }

    @Transactional()
    @Override
    public DetalleOrden save(DetalleOrden detalleorden) throws GlobalExceptions {
        if (detalleorden == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        return detalleordenRepository.save(detalleorden);
    }

    @Transactional
    @Override
    public DetalleOrden update(int id, DetalleOrden newDetalleOrden) throws GlobalExceptions {
        DetalleOrden detalleorden = detalleordenRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El detalle de la orden que intenta actualizar no existe"));
        detalleorden.setServicio(newDetalleOrden.getServicio());
        detalleorden.setOrdenServicio(newDetalleOrden.getOrdenServicio());
        return detalleordenRepository.save(detalleorden);
    }

    @Transactional
    @Override
    public DetalleOrden delete(int id) throws GlobalExceptions {
        DetalleOrden detalleorden = detalleordenRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El detalle de la orden que intenta eliminar no existe"));
        detalleordenRepository.delete(detalleorden);
        return detalleorden;
    }
}
