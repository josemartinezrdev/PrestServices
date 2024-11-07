package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.EmailPersona;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IEmailPersona {
    List<EmailPersona> findAll();

    EmailPersona findById(int id) throws GlobalExceptions;

    EmailPersona save(EmailPersona emailpersona) throws GlobalExceptions;

    EmailPersona update(int id, EmailPersona emailpersona);

    EmailPersona delete(int id);
}
