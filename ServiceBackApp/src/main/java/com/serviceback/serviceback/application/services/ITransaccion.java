package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.Transaccion;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface ITransaccion {
    List<Transaccion> findAll();
    
    List<Transaccion> findTransaccionByIdServicio(int idservicio);

    Transaccion findById(int id) throws GlobalExceptions;

    Transaccion save(Transaccion transaccion) throws GlobalExceptions;

    Transaccion update(int id, Transaccion transaccion);

    Transaccion delete(int id);

    List<Transaccion> findByProveedorIdAndEstadoId(String proveedorId, int estadoId);
}
