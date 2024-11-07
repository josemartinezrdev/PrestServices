package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.DetalleOrden;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IDetalleOrden {
    List<DetalleOrden> findAll();

    DetalleOrden findById(int id) throws GlobalExceptions;

    DetalleOrden save(DetalleOrden detalleorden) throws GlobalExceptions;

    DetalleOrden update(int id, DetalleOrden detalleorden);

    DetalleOrden delete(int id);
}
