package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.Ciudad;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface ICiudad {
    
    List<Ciudad> findAll();

    Ciudad findById(int id) throws GlobalExceptions;

    Ciudad save(Ciudad ciudad) throws GlobalExceptions;

    Ciudad update(int id, Ciudad ciudad);

    Ciudad delete(int id);
}
