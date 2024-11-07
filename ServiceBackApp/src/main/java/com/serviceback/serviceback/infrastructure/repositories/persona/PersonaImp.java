package com.serviceback.serviceback.infrastructure.repositories.persona;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IPersona;
import com.serviceback.serviceback.domain.entities.Persona;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class PersonaImp implements IPersona {
    @Autowired
    private PersonaRepository personaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Persona> findAll() {
        List<Persona> personas = personaRepository.findAll();
        if (personas.isEmpty()) {
            throw new GlobalExceptions("No se encontraron personas.");
        }
        return personas;
    }

    @Transactional(readOnly = true)
    @Override
    public Persona findById(String nrodoc) throws GlobalExceptions {
        return personaRepository.findById(nrodoc)
                .orElseThrow(() -> new GlobalExceptions("La persona que intenta buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Persona save(Persona persona) throws GlobalExceptions {
        if (persona == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        if (personaRepository.existsByNombre(persona.getNombre())) {
            throw new GlobalExceptions("El valor ya existe");
        }

        return personaRepository.save(persona);
    }

    @Transactional
    @Override
    public Persona update(String nrodoc, Persona newPersona) throws GlobalExceptions {
        Persona persona = personaRepository.findById(nrodoc)
                .orElseThrow(() -> new GlobalExceptions("La persona que intenta actualizar no existe"));
        persona.setNombre(newPersona.getNombre());
        persona.setApellido(newPersona.getApellido());
        persona.setTipoPersona(newPersona.getTipoPersona());
        return personaRepository.save(persona);
    }

    @Transactional
    @Override
    public Persona delete(String nrodoc) throws GlobalExceptions {
        Persona persona = personaRepository.findById(nrodoc)
                .orElseThrow(() -> new GlobalExceptions("La persona que intentas eliminar no existe"));
        personaRepository.delete(persona);
        return persona;
    }

    @Override
    public Persona findPersonaByUsername(String username) throws GlobalExceptions {
        return personaRepository.findByUserUsername(username);
    }

    @Override
    public List<Persona> findByRole(int id) {

        return personaRepository.findByRole(id);
    }
}
