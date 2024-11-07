package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.OrdenServicio;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IOrdenServicio {
    List<OrdenServicio> findAll();

    OrdenServicio findById(int norden) throws GlobalExceptions;

    OrdenServicio save(OrdenServicio ordenservicio) throws GlobalExceptions;

    OrdenServicio update(int norden, OrdenServicio ordenservicio);

    OrdenServicio delete(int norden);

    List<OrdenServicio> findByEstadoOrden_Id(Integer estadoOrdenId);

}