package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.TelefonoSucursal;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface ITelefonoSucursal {
    List<TelefonoSucursal> findAll();

    TelefonoSucursal findById(int id) throws GlobalExceptions;

    TelefonoSucursal save(TelefonoSucursal telefonoSucursal) throws GlobalExceptions;

    TelefonoSucursal update(int id, TelefonoSucursal telefonoSucursal);

    TelefonoSucursal delete(int id);
}
