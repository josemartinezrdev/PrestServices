package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.TipoEmail;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface ITipoEmail {
    List<TipoEmail> findAll();

    TipoEmail findById(int id) throws GlobalExceptions;

    TipoEmail save(TipoEmail tipoEmail) throws GlobalExceptions;

    TipoEmail update(int id, TipoEmail tipoEmail);

    TipoEmail delete(int id);
}
