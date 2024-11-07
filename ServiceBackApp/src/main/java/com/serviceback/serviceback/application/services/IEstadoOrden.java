package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.EstadoOrden;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IEstadoOrden {

    List<EstadoOrden> findAll();

    EstadoOrden findById(int id);

    EstadoOrden update(int id, EstadoOrden estadoorden) throws GlobalExceptions;

    EstadoOrden save(EstadoOrden estadoorden) throws GlobalExceptions;

    EstadoOrden delete(int id);
}
