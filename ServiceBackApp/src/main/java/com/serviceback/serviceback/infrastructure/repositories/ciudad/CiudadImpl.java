package com.serviceback.serviceback.infrastructure.repositories.ciudad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.ICiudad;
import com.serviceback.serviceback.domain.entities.Ciudad;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class CiudadImpl implements ICiudad {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Ciudad> findAll() throws GlobalExceptions {
        List<Ciudad> ciudades = (List<Ciudad>) ciudadRepository.findAll();
        if (ciudades.isEmpty()) {
            throw new GlobalExceptions("No se encontraron ciudades.");
        }
        return ciudades;
    }

    @Transactional(readOnly = true)
    @Override
    public Ciudad findById(int id) throws GlobalExceptions {
        return ciudadRepository.findById(id).orElseThrow(() -> new GlobalExceptions("El Ciudad no existe"));

    }

    @Transactional
    @Override
    public Ciudad save(Ciudad ciudad) throws GlobalExceptions {
        if (ciudad == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return ciudadRepository.save(ciudad);
    }

    @Transactional
    @Override
    public Ciudad update(int id, Ciudad ciudad) throws GlobalExceptions {
        Ciudad newCiudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El Ciudad que intentas actualizar no existe"));
        newCiudad.setNombre(ciudad.getNombre());
        newCiudad.setRegion(ciudad.getRegion());
        return ciudadRepository.save(newCiudad);
    }

    @Transactional
    @Override
    public Ciudad delete(int id) throws GlobalExceptions {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El ciudad que intentas eliminar no existe"));
        ciudadRepository.delete(ciudad);
        return ciudad;
    }

}
