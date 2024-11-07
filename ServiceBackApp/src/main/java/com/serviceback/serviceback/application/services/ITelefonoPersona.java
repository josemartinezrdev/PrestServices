package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.TelefonoPersona;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface ITelefonoPersona {
    List<TelefonoPersona> findAll();

    TelefonoPersona findById(int id) throws GlobalExceptions;

    TelefonoPersona save(TelefonoPersona telefonopersona) throws GlobalExceptions;

    TelefonoPersona update(int id, TelefonoPersona telefonopersona);

    TelefonoPersona delete(int id);
}
