package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.Persona;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IPersona {
    List<Persona> findAll();

    Persona findById(String nrodoc) throws GlobalExceptions;

    Persona findPersonaByUsername(String username) throws GlobalExceptions;

    Persona save(Persona persona) throws GlobalExceptions;

    Persona update(String nrodoc, Persona persona);

    Persona delete(String nrodoc);

    List<Persona> findByRole(int id);

}
