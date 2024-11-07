package com.serviceback.serviceback.infrastructure.repositories.sucursal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.ISucursal;
import com.serviceback.serviceback.domain.entities.Sucursal;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class SucursalImp implements ISucursal {
    @Autowired
    private SucursalRepository sucursalRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Sucursal> findAll() {
        List<Sucursal> sucursals = sucursalRepository.findAll();
        if (sucursals.isEmpty()) {
            throw new GlobalExceptions("No se encontraron sucursales.");
        }
        return sucursals;
    }

    @Transactional(readOnly = true)
    @Override
    public Sucursal findById(int id) throws GlobalExceptions {
        return sucursalRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La sucursal que intenta buscar no existe"));
    }

    @Override
    public Sucursal save(Sucursal sucursal) throws GlobalExceptions {
        if (sucursal == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal update(int id, Sucursal newSucursal) throws GlobalExceptions {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La sucursal que intenta actualizar no existe"));
        sucursal.setNombre(newSucursal.getNombre());
        sucursal.setNit(newSucursal.getNit());
        sucursal.setDireccion(newSucursal.getDireccion());
        sucursal.setEmpresa(newSucursal.getEmpresa());
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal delete(int id) throws GlobalExceptions {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La sucursal que intenta eliminar no existe"));
        sucursalRepository.delete(sucursal);
        return sucursal;
    }
}
