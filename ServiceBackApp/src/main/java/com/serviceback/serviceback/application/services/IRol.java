package com.serviceback.serviceback.application.services;

import java.util.List;
import com.serviceback.serviceback.domain.entities.security.Rol;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IRol {

    Rol findByNombre(String nombre);

    List<Rol> findAll();

    Rol findById(int id) throws GlobalExceptions;

    Rol save(Rol rol) throws GlobalExceptions;

    Rol update(int id, Rol rol);

    Rol delete(int id);
}
