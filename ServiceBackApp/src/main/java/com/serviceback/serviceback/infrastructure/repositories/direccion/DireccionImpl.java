package com.serviceback.serviceback.infrastructure.repositories.direccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IDireccion;
import com.serviceback.serviceback.domain.entities.Direccion;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class DireccionImpl implements IDireccion {

    @Autowired
    private DireccionRepository direccionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Direccion> findAll() throws GlobalExceptions {
        List<Direccion> direcciones = (List<Direccion>) direccionRepository.findAll();
        if (direcciones.isEmpty()) {
            throw new GlobalExceptions("No se encontraron direcciones.");
        }
        return direcciones;
    }

    @Transactional(readOnly = true)
    @Override
    public Direccion findById(int id) throws GlobalExceptions {
        return direccionRepository.findById(id).orElseThrow(() -> new GlobalExceptions("El Direccion no existe"));

    }

    @Transactional
    @Override
    public Direccion save(Direccion direccion) throws GlobalExceptions {
        if (direccion == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return direccionRepository.save(direccion);
    }

    @Transactional
    @Override
    public Direccion update(int id, Direccion direccion) throws GlobalExceptions {
        Direccion newDireccion = direccionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El Direccion que intentas actualizar no existe"));
        newDireccion.setCalle(newDireccion.getCalle());
        newDireccion.setCarrera(newDireccion.getCarrera());
        newDireccion.setCiudad(newDireccion.getCiudad());
        return direccionRepository.save(newDireccion);
    }

    @Transactional
    @Override
    public Direccion delete(int id) throws GlobalExceptions {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El direccion que intentas eliminar no existe"));
        direccionRepository.delete(direccion);
        return direccion;
    }

}
