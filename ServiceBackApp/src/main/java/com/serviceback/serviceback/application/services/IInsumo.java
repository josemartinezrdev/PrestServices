package com.serviceback.serviceback.application.services;

import java.util.List;


import com.serviceback.serviceback.domain.entities.Insumo;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IInsumo {

    List<Insumo> findInsumosByService(int idinsumo);

    List<Insumo> findAll();

    Insumo findById(int id) throws GlobalExceptions;

    Insumo save(Insumo insumo) throws GlobalExceptions;

    Insumo update(int id, Insumo insumo);

    Insumo delete(int id);

    List<Insumo> findInsumosByProveedor(String nrodoc);
}
