package com.serviceback.serviceback.infrastructure.repositories.transaccion;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.ITransaccion;
import com.serviceback.serviceback.domain.entities.Transaccion;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class TransaccionImp implements ITransaccion {
    @Autowired
    private TransaccionRepository transaccionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Transaccion> findAll() {
        List<Transaccion> transaccions = transaccionRepository.findAll();
        if (transaccions.isEmpty()) {
            throw new GlobalExceptions("No se encontraron transaccions.");
        }
        return transaccions;
    }

    @Transactional(readOnly = true)
    @Override
    public Transaccion findById(int id) throws GlobalExceptions {
        return transaccionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La transaccion que intentas buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Transaccion save(Transaccion transaccion) throws GlobalExceptions {
        if (transaccion == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        return transaccionRepository.save(transaccion);
    }

    @Transactional
    @Override
    public Transaccion update(int id, Transaccion newTransaccion) throws GlobalExceptions {
        Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La transaccion que intentas actualizar no existe"));
        transaccion.setPersona(newTransaccion.getPersona());
        transaccion.setProveedor(newTransaccion.getProveedor());
        transaccion.setEstadoAprobacion(newTransaccion.getEstadoAprobacion());
        return transaccionRepository.save(transaccion);
    }

    @Transactional
    @Override
    public Transaccion delete(int id) throws GlobalExceptions {
        Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La transaccion que intentas eliminar no existe"));
        transaccionRepository.delete(transaccion);
        return transaccion;
    }

    @Override
    public List<Transaccion> findByProveedorIdAndEstadoId(String idproveedor, int estadoId) {
        return transaccionRepository.findByProveedorIdAndEstadoId(idproveedor, estadoId);
    }

    @Override
    public List<Transaccion> findTransaccionByIdServicio(int idservicio) {
        return transaccionRepository.findTransaccionByIdServicio(idservicio);
    }
}
