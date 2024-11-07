package com.serviceback.serviceback.infrastructure.repositories.tipotelefono;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.ITipoTelefono;
import com.serviceback.serviceback.domain.entities.TipoTelefono;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class TipoTelefonoImp implements ITipoTelefono {

    @Autowired
    private TipoTelefonoRepository tipoTelefonoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoTelefono> findAll() {
        List<TipoTelefono> tipoTelefonos = tipoTelefonoRepository.findAll();
        if (tipoTelefonos.isEmpty()) {
            throw new GlobalExceptions("No se encontraron tipos de teléfono.");
        }
        return tipoTelefonos;
    }

    @Transactional(readOnly = true)
    @Override
    public TipoTelefono findById(int id) throws GlobalExceptions {
        return tipoTelefonoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de teléfono que intentas buscar no existe"));
    }

    @Override
    public TipoTelefono save(TipoTelefono tipoTelefono) throws GlobalExceptions {
        if (tipoTelefono == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return tipoTelefonoRepository.save(tipoTelefono);
    }

    @Override
    public TipoTelefono update(int id, TipoTelefono newTipoTelefono) throws GlobalExceptions {
        TipoTelefono tipoTelefono = tipoTelefonoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de teléfono que intentas actualizar no existe"));
        tipoTelefono.setDescripcion(newTipoTelefono.getDescripcion());
        return tipoTelefonoRepository.save(tipoTelefono);
    }

    @Override
    public TipoTelefono delete(int id) throws GlobalExceptions {
        TipoTelefono tipoTelefono = tipoTelefonoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de teléfono que intentas eliminar no existe"));
        tipoTelefonoRepository.delete(tipoTelefono);
        return tipoTelefono;
    }
}
