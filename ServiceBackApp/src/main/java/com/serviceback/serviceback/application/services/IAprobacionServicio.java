package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.AprobacionServicio;
import com.serviceback.serviceback.domain.entities.Servicio;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IAprobacionServicio {

    List<AprobacionServicio> findByCliente(String nrodoc, int idestado);

    Servicio findAprobacionesWithServiceByNorden(int norden);

    List<AprobacionServicio> findAll();

    AprobacionServicio findById(int id) throws GlobalExceptions;

    AprobacionServicio save(AprobacionServicio aprobacionservicio) throws GlobalExceptions;

    AprobacionServicio update(int id, AprobacionServicio aprobacionservicio);

    AprobacionServicio delete(int id);

}