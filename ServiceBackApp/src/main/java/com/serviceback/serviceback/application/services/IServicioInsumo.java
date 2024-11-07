package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.ServicioInsumo;
import com.serviceback.serviceback.domain.entities.fkClass.ServicioInsumoPK;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IServicioInsumo {
    List<ServicioInsumo> findAll();

    ServicioInsumo findById(ServicioInsumoPK id) throws GlobalExceptions;

    ServicioInsumo save(ServicioInsumo servicioinsumo) throws GlobalExceptions;

    ServicioInsumo update(ServicioInsumoPK id, ServicioInsumo servicioinsumo);

    ServicioInsumo delete(ServicioInsumoPK id);
}
