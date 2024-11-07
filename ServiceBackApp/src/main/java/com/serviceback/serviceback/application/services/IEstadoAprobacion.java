package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.EstadoAprobacion;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IEstadoAprobacion {
     List<EstadoAprobacion> findAll();

    EstadoAprobacion findById(int id) throws GlobalExceptions;

    EstadoAprobacion save(EstadoAprobacion estadoaprobacion) throws GlobalExceptions;

    EstadoAprobacion update(int id, EstadoAprobacion estadoaprobacion);

    EstadoAprobacion delete(int id);

}
