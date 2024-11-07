package com.serviceback.serviceback.infrastructure.repositories.detalleordentrabajo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IDetalleOrdenTrabajo;
import com.serviceback.serviceback.domain.entities.DetalleOrdenTrabajo;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class DetalleOrdenTrabajoImp implements IDetalleOrdenTrabajo {

    @Autowired
    private DetalleOrdenTrabajoRepository detalleordentrabajoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<DetalleOrdenTrabajo> findAll() {
        List<DetalleOrdenTrabajo> detalleordentrabajos = detalleordentrabajoRepository.findAll();
        if (detalleordentrabajos.isEmpty()) {
            throw new GlobalExceptions("No se encontraron valores.");
        }
        return detalleordentrabajos;
    }

    @Transactional(readOnly = true)
    @Override
    public DetalleOrdenTrabajo findById(int id) throws GlobalExceptions {
        return detalleordentrabajoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La Valor que intenta buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public DetalleOrdenTrabajo save(DetalleOrdenTrabajo detalleordentrabajo) throws GlobalExceptions {
        if (detalleordentrabajo == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return detalleordentrabajoRepository.save(detalleordentrabajo);
    }

    @Transactional
    @Override
    public DetalleOrdenTrabajo update(int id, DetalleOrdenTrabajo newDetalleOrdenTrabajo) throws GlobalExceptions {
        DetalleOrdenTrabajo detalleordentrabajo = detalleordentrabajoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El valor que intenta actualizar no existe"));
        detalleordentrabajo.setOrdenTrabajo(newDetalleOrdenTrabajo.getOrdenTrabajo());
        detalleordentrabajo.setServicio(newDetalleOrdenTrabajo.getServicio());
        detalleordentrabajo.setEstadoSerOrden(newDetalleOrdenTrabajo.getEstadoSerOrden());
        detalleordentrabajo.setEmpleado(newDetalleOrdenTrabajo.getEmpleado());
        return detalleordentrabajoRepository.save(detalleordentrabajo);
    }

    @Transactional
    @Override
    public DetalleOrdenTrabajo delete(int id) throws GlobalExceptions {
        DetalleOrdenTrabajo detalleordentrabajo = detalleordentrabajoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El valor que intentas eliminar no existe"));
        detalleordentrabajoRepository.delete(detalleordentrabajo);
        return detalleordentrabajo;
    }

    @Override
    public List<DetalleOrdenTrabajo> findByEmpleadoNrodoc(String nrodoc, int idestado) {
        return detalleordentrabajoRepository.findByEmpleadoNrodocAndEstadoSerOrdenId(nrodoc, idestado);
    }
}
