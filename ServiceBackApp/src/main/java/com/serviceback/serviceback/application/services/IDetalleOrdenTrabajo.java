package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.DetalleOrdenTrabajo;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IDetalleOrdenTrabajo {

    List<DetalleOrdenTrabajo> findAll();

    List<DetalleOrdenTrabajo> findByEmpleadoNrodoc(String nrodoc, int idestado);

    DetalleOrdenTrabajo findById(int id) throws GlobalExceptions;

    DetalleOrdenTrabajo save(DetalleOrdenTrabajo detalleordentrabajo) throws GlobalExceptions;

    DetalleOrdenTrabajo update(int id, DetalleOrdenTrabajo detalleordentrabajo);

    DetalleOrdenTrabajo delete(int id);

}