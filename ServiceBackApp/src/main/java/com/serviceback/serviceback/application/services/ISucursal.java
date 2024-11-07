package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.Sucursal;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface ISucursal {
    List<Sucursal> findAll();

    Sucursal findById(int id) throws GlobalExceptions;

    Sucursal save(Sucursal sucursal) throws GlobalExceptions;

    Sucursal update(int id, Sucursal sucursal);

    Sucursal delete(int id);
}
