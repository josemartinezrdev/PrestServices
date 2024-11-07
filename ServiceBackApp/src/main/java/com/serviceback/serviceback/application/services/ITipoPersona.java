package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.TipoPersona;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface ITipoPersona {
    List<TipoPersona> findAll();

    TipoPersona findById(int id) throws GlobalExceptions;

    TipoPersona save(TipoPersona tipoPersona) throws GlobalExceptions;

    TipoPersona update(int id, TipoPersona tipoPersona);

    TipoPersona delete(int id);
}
