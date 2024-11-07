package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.Pais;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IPais {

    List<Pais> findAll();

    Pais findById(int id) throws GlobalExceptions;

    Pais save(Pais pais) throws GlobalExceptions;

    Pais update(int id, Pais pais);

    Pais delete(int id);

}
