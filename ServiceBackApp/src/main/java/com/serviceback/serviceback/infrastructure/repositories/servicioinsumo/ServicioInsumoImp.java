package com.serviceback.serviceback.infrastructure.repositories.servicioinsumo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IServicioInsumo;
import com.serviceback.serviceback.domain.entities.ServicioInsumo;
import com.serviceback.serviceback.domain.entities.fkClass.ServicioInsumoPK;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class ServicioInsumoImp implements IServicioInsumo {
    @Autowired
    private ServicioInsumoRepository servicioinsumoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ServicioInsumo> findAll() {
        List<ServicioInsumo> servicioinsumos = servicioinsumoRepository.findAll();
        if (servicioinsumos.isEmpty()) {
            throw new GlobalExceptions("No se encontraron campos.");
        }
        return servicioinsumos;
    }

    @Transactional(readOnly = true)
    @Override
    public ServicioInsumo findById(ServicioInsumoPK id) throws GlobalExceptions {
        return servicioinsumoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas buscar no existe"));
    }

    @Override
    public ServicioInsumo save(ServicioInsumo servicioinsumo) throws GlobalExceptions {
        if (servicioinsumo == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return servicioinsumoRepository.save(servicioinsumo);
    }

    @Override
    public ServicioInsumo update(ServicioInsumoPK id, ServicioInsumo newServicioInsumo) throws GlobalExceptions {
        ServicioInsumo servicioinsumo = servicioinsumoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas actualizar no existe"));
        servicioinsumo.setCantidadinsumo(newServicioInsumo.getCantidadinsumo());
        servicioinsumo.setValortotal(newServicioInsumo.getValortotal());
        servicioinsumo.setServicio(newServicioInsumo.getServicio());
        servicioinsumo.setInsumo(newServicioInsumo.getInsumo());
        return servicioinsumoRepository.save(servicioinsumo);
    }

    @Override
    public ServicioInsumo delete(ServicioInsumoPK id) throws GlobalExceptions {
        ServicioInsumo servicioinsumo = servicioinsumoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas eliminar no existe"));
        servicioinsumoRepository.delete(servicioinsumo);
        return servicioinsumo;
    }
}
