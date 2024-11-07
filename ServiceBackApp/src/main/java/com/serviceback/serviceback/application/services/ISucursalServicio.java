package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.SucursalServicio;
import com.serviceback.serviceback.domain.entities.fkClass.SucursalServicioPK;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface ISucursalServicio {

    List<SucursalServicio> findAll();

    SucursalServicio findById(SucursalServicioPK id) throws GlobalExceptions;

    SucursalServicio save(SucursalServicio sucursalservicio) throws GlobalExceptions;

    SucursalServicio update(SucursalServicioPK id, SucursalServicio sucursalservicio);

    SucursalServicio delete(SucursalServicioPK id);
}
