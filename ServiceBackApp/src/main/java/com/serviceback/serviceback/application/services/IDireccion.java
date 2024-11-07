package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.Direccion;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IDireccion {
     List<Direccion> findAll();

    Direccion findById(int id);

    Direccion update(int id, Direccion direccion) throws GlobalExceptions;

    Direccion save(Direccion direccion) throws GlobalExceptions;

    Direccion delete(int id);

}
