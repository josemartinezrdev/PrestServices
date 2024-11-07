package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.Servicio;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IServicio {
    List<Servicio> findAll();

    Servicio findById(int id) throws GlobalExceptions;

    Servicio save(Servicio servicio) throws GlobalExceptions;

    Servicio update(int id, Servicio servicio);

    Servicio delete(int id);
}
