
package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.Empresa;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IEmpresa {
    List<Empresa> findAll();

    Empresa findById(int id) throws GlobalExceptions;

    Empresa save(Empresa empresa) throws GlobalExceptions;

    Empresa update(int id, Empresa empresa);

    Empresa delete(int id);
}
