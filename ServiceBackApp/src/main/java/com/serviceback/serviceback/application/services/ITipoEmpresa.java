package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.TipoEmpresa;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface ITipoEmpresa {
    List<TipoEmpresa> findAll();

    TipoEmpresa findById(int id) throws GlobalExceptions;

    TipoEmpresa save(TipoEmpresa tipoEmpresa) throws GlobalExceptions;

    TipoEmpresa update(int id, TipoEmpresa tipoEmpresa);

    TipoEmpresa delete(int id);
}
