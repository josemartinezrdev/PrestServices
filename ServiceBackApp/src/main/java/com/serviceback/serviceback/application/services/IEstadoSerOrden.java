package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.EstadoSerOrden;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IEstadoSerOrden {
    
 List<EstadoSerOrden> findAll();

    EstadoSerOrden findById(int id);

    EstadoSerOrden update(int id, EstadoSerOrden estadoserorden) throws GlobalExceptions;

    EstadoSerOrden save(EstadoSerOrden estadoserorden) throws GlobalExceptions;

    EstadoSerOrden delete(int id);

}
