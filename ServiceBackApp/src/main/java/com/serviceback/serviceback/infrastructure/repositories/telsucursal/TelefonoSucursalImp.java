package com.serviceback.serviceback.infrastructure.repositories.telsucursal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.ITelefonoSucursal;
import com.serviceback.serviceback.domain.entities.TelefonoSucursal;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class TelefonoSucursalImp implements ITelefonoSucursal {
    @Autowired
    private TelefonoSucursalRepository telefonoSucursalRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TelefonoSucursal> findAll() {
        List<TelefonoSucursal> telefonoSucursals = telefonoSucursalRepository.findAll();
        if (telefonoSucursals.isEmpty()) {
            throw new GlobalExceptions("No se encontraron telefonos de sucursal.");
        }
        return telefonoSucursals;
    }

    @Transactional(readOnly = true)
    @Override
    public TelefonoSucursal findById(int id) throws GlobalExceptions {
        return telefonoSucursalRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El telefono de la sucursal que intentas buscar no existe"));
    }

    @Override
    public TelefonoSucursal save(TelefonoSucursal telefonoSucursal) throws GlobalExceptions {
        if (telefonoSucursal == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return telefonoSucursalRepository.save(telefonoSucursal);
    }

    @Override
    public TelefonoSucursal update(int id, TelefonoSucursal newTelefonoSucursal) throws GlobalExceptions {
        TelefonoSucursal telefonoSucursal = telefonoSucursalRepository.findById(id)
                .orElseThrow(
                        () -> new GlobalExceptions("El telefono de la sucursal que intentas actualizar no existe"));
        telefonoSucursal.setTelefono(newTelefonoSucursal.getTelefono());
        telefonoSucursal.setSucursal(newTelefonoSucursal.getSucursal());
        telefonoSucursal.setTipoTelefono(newTelefonoSucursal.getTipoTelefono());
        return telefonoSucursalRepository.save(telefonoSucursal);
    }

    @Override
    public TelefonoSucursal delete(int id) throws GlobalExceptions {
        TelefonoSucursal telefonoSucursal = telefonoSucursalRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El telefono de la sucursal que intentas eliminar no existe"));
        telefonoSucursalRepository.delete(telefonoSucursal);
        return telefonoSucursal;
    }
}
