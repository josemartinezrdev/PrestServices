package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.TipoTelefono;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface ITipoTelefono {
    List<TipoTelefono> findAll();

    TipoTelefono findById(int id) throws GlobalExceptions;

    TipoTelefono save(TipoTelefono tipoTelefono) throws GlobalExceptions;

    TipoTelefono update(int id, TipoTelefono tipoTelefono);

    TipoTelefono delete(int id);
}
