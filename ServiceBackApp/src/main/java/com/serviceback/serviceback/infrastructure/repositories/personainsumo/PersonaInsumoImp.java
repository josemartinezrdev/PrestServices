package com.serviceback.serviceback.infrastructure.repositories.personainsumo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IPersonaInsumo;
import com.serviceback.serviceback.domain.entities.PersonaInsumo;
import com.serviceback.serviceback.domain.entities.fkClass.PersonaInsumoPK;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class PersonaInsumoImp implements IPersonaInsumo {
    @Autowired
    private PersonaInsumoRepository personainsumoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PersonaInsumo> findAll() {
        List<PersonaInsumo> personainsumos = personainsumoRepository.findAll();
        if (personainsumos.isEmpty()) {
            throw new GlobalExceptions("No se encontraron campos.");
        }
        return personainsumos;
    }

    @Transactional(readOnly = true)
    @Override
    public PersonaInsumo findById(PersonaInsumoPK id) throws GlobalExceptions {
        return personainsumoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas buscar no existe"));
    }

    @Override
    public PersonaInsumo save(PersonaInsumo personainsumo) throws GlobalExceptions {
        if (personainsumo == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return personainsumoRepository.save(personainsumo);
    }

    @Override
    public PersonaInsumo update(PersonaInsumoPK id, PersonaInsumo newPersonaInsumo) throws GlobalExceptions {
        PersonaInsumo personainsumo = personainsumoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas actualizar no existe"));
        personainsumo.setServicio(newPersonaInsumo.getServicio());
        personainsumo.setInsumo(newPersonaInsumo.getInsumo());
        personainsumo.setNrodoc(newPersonaInsumo.getNrodoc());
        return personainsumoRepository.save(personainsumo);
    }

    @Override
    public PersonaInsumo delete(PersonaInsumoPK id) throws GlobalExceptions {
        PersonaInsumo personainsumo = personainsumoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas eliminar no existe"));
        personainsumoRepository.delete(personainsumo);
        return personainsumo;
    }
}
