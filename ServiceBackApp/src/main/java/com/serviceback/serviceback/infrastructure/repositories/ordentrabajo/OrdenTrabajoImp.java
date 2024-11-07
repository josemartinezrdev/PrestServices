package com.serviceback.serviceback.infrastructure.repositories.ordentrabajo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IOrdenTrabajo;
import com.serviceback.serviceback.domain.entities.OrdenTrabajo;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class OrdenTrabajoImp implements IOrdenTrabajo {

    @Autowired
    private OrdenTrabajoRepository ordentrabajoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<OrdenTrabajo> findAll() {
        List<OrdenTrabajo> ordentrabajos = ordentrabajoRepository.findAll();
        if (ordentrabajos.isEmpty()) {
            throw new GlobalExceptions("No se encontraron valores.");
        }
        return ordentrabajos;
    }

    @Transactional(readOnly = true)
    @Override
    public OrdenTrabajo findById(int id) throws GlobalExceptions {
        return ordentrabajoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La Valor que intenta buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public OrdenTrabajo save(OrdenTrabajo ordentrabajo) throws GlobalExceptions {
        if (ordentrabajo == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return ordentrabajoRepository.save(ordentrabajo);
    }

    @Transactional
    @Override
    public OrdenTrabajo update(int id, OrdenTrabajo newOrdenTrabajo) throws GlobalExceptions {
        OrdenTrabajo ordentrabajo = ordentrabajoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El valor que intenta actualizar no existe"));
        ordentrabajo.setNrotrabajo(newOrdenTrabajo.getNrotrabajo());
        ordentrabajo.setFechaasignacion(newOrdenTrabajo.getFechaasignacion());
        ordentrabajo.setHoraasignacion(newOrdenTrabajo.getHoraasignacion());
        ordentrabajo.setEmpleado(newOrdenTrabajo.getEmpleado());
        ordentrabajo.setOrdenServicio(newOrdenTrabajo.getOrdenServicio());
        return ordentrabajoRepository.save(ordentrabajo);
    }

    @Transactional
    @Override
    public OrdenTrabajo delete(int id) throws GlobalExceptions {
        OrdenTrabajo ordentrabajo = ordentrabajoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El valor que intentas eliminar no existe"));
        ordentrabajoRepository.delete(ordentrabajo);
        return ordentrabajo;
    }
}
