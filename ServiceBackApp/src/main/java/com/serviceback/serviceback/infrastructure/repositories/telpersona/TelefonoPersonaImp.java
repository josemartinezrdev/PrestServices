package com.serviceback.serviceback.infrastructure.repositories.telpersona;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.ITelefonoPersona;
import com.serviceback.serviceback.domain.entities.TelefonoPersona;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class TelefonoPersonaImp implements ITelefonoPersona {
    @Autowired
    private TelefonoPersonaRepository telefonoPersonaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TelefonoPersona> findAll() {
        List<TelefonoPersona> telefonoPersonas = telefonoPersonaRepository.findAll();
        if (telefonoPersonas.isEmpty()) {
            throw new GlobalExceptions("No se encontraron telefonos de persona.");
        }
        return telefonoPersonas;
    }

    @Transactional(readOnly = true)
    @Override
    public TelefonoPersona findById(int id) throws GlobalExceptions {
        return telefonoPersonaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El telefono de la persona que intenta buscar no existe"));
    }

    @Override
    public TelefonoPersona save(TelefonoPersona telefonoPersona) throws GlobalExceptions {
        if (telefonoPersona == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return telefonoPersonaRepository.save(telefonoPersona);
    }

    @Override
    public TelefonoPersona update(int id, TelefonoPersona newTelefonoPersona) throws GlobalExceptions {
        TelefonoPersona telefonoPersona = telefonoPersonaRepository.findById(id)
                .orElseThrow(
                        () -> new GlobalExceptions("El telefono de la persona que intenta actualizar no existe"));
        telefonoPersona.setNro(newTelefonoPersona.getNro());
        telefonoPersona.setTipoTelefono(newTelefonoPersona.getTipoTelefono());
        telefonoPersona.setPersona(newTelefonoPersona.getPersona());
        return telefonoPersonaRepository.save(telefonoPersona);
    }

    @Override
    public TelefonoPersona delete(int id) throws GlobalExceptions {
        TelefonoPersona telefonoPersona = telefonoPersonaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El telefono de la persona que intenta eliminar no existe"));
        telefonoPersonaRepository.delete(telefonoPersona);
        return telefonoPersona;
    }
}
