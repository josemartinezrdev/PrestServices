package com.serviceback.serviceback.infrastructure.repositories.pais;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IPais;
import com.serviceback.serviceback.domain.entities.Pais;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class PaisImpl implements IPais {

    @Autowired
    private PaisRepository paisRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Pais> findAll() throws GlobalExceptions {
        List<Pais> paises = (List<Pais>) paisRepository.findAll();
        if (paises.isEmpty()) {
            throw new GlobalExceptions("No se encontraron paises.");
        }
        return paises;
    }

    @Transactional(readOnly = true)
    @Override
    public Pais findById(int id) throws GlobalExceptions {
        return paisRepository.findById(id).orElseThrow(() -> new GlobalExceptions("El Pais no existe"));

    }

    @Transactional
    @Override
    public Pais save(Pais pais) throws GlobalExceptions {
        if (pais == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return paisRepository.save(pais);
    }

    @Transactional
    @Override
    public Pais update(int id, Pais pais) throws GlobalExceptions {
        Pais newPais = paisRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El Pais que intentas actualizar no existe"));
        newPais.setNombre(pais.getNombre());
        return paisRepository.save(newPais);
    }

    @Transactional
    @Override
    public Pais delete(int id) throws GlobalExceptions {
        Pais pais = paisRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El pais que intentas eliminar no existe"));
        paisRepository.delete(pais);
        return pais;
    }

}
