package com.serviceback.serviceback.infrastructure.repositories.tipopersona;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.ITipoPersona;
import com.serviceback.serviceback.domain.entities.TipoPersona;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class TipoPersonaImp implements ITipoPersona {
    @Autowired
    private TipoPersonaRepository tipoPersonaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoPersona> findAll() {
        List<TipoPersona> tipoPersonas = tipoPersonaRepository.findAll();
        if (tipoPersonas.isEmpty()) {
            throw new GlobalExceptions("No se encontraron tipos de persona.");
        }
        return tipoPersonas;
    }

    @Transactional(readOnly = true)
    @Override
    public TipoPersona findById(int id) throws GlobalExceptions {
        return tipoPersonaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de persona que intentas buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public TipoPersona save(TipoPersona tipoPersona) throws GlobalExceptions {
        if (tipoPersona == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        if (tipoPersonaRepository.existsByNombre(tipoPersona.getNombre())) {
            throw new GlobalExceptions("El valor no puede duplicarse");
        }
        return tipoPersonaRepository.save(tipoPersona);
    }

    @Transactional
    @Override
    public TipoPersona update(int id, TipoPersona newTipoPersona) throws GlobalExceptions {
        TipoPersona tipoPersona = tipoPersonaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de persona que intentas actualizar no existe"));

        tipoPersona.setNombre(newTipoPersona.getNombre());
        return tipoPersonaRepository.save(tipoPersona);
    }

    @Transactional
    @Override
    public TipoPersona delete(int id) throws GlobalExceptions {
        TipoPersona tipoPersona = tipoPersonaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de persona que intentas eliminar no existe"));
        tipoPersonaRepository.delete(tipoPersona);
        return tipoPersona;
    }
}
