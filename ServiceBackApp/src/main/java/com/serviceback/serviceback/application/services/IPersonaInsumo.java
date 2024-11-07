package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.PersonaInsumo;
import com.serviceback.serviceback.domain.entities.fkClass.PersonaInsumoPK;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IPersonaInsumo {
    List<PersonaInsumo> findAll();

    PersonaInsumo findById(PersonaInsumoPK id) throws GlobalExceptions;

    PersonaInsumo save(PersonaInsumo personainsumo) throws GlobalExceptions;

    PersonaInsumo update(PersonaInsumoPK id, PersonaInsumo personainsumo);

    PersonaInsumo delete(PersonaInsumoPK id);
}
