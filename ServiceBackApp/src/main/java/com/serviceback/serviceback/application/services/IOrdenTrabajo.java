package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.OrdenTrabajo;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IOrdenTrabajo {
    List<OrdenTrabajo> findAll();

    OrdenTrabajo findById(int id) throws GlobalExceptions;

    OrdenTrabajo save(OrdenTrabajo ordentrabajo) throws GlobalExceptions;

    OrdenTrabajo update(int id, OrdenTrabajo ordentrabajo);

    OrdenTrabajo delete(int id);

}