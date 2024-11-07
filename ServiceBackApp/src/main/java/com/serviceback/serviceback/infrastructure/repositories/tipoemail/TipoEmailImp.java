package com.serviceback.serviceback.infrastructure.repositories.tipoemail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.ITipoEmail;
import com.serviceback.serviceback.domain.entities.TipoEmail;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class TipoEmailImp implements ITipoEmail {
    @Autowired
    private TipoEmailRepository tipoEmailRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoEmail> findAll() {
        List<TipoEmail> tipoEmails = tipoEmailRepository.findAll();
        if (tipoEmails.isEmpty()) {
            throw new GlobalExceptions("No se encontraron tipos de email.");
        }
        return tipoEmails;
    }

    @Transactional(readOnly = true)
    @Override
    public TipoEmail findById(int id) throws GlobalExceptions {
        return tipoEmailRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de email que intentas buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public TipoEmail save(TipoEmail tipoEmail) throws GlobalExceptions {
        if (tipoEmail == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        if (tipoEmailRepository.existsByNombre(tipoEmail.getNombre())) {
            throw new GlobalExceptions("El valor ya existe");
        }

        return tipoEmailRepository.save(tipoEmail);
    }

    @Override
    public TipoEmail update(int id, TipoEmail newTipoEmail) throws GlobalExceptions {
        TipoEmail tipoEmail = tipoEmailRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de email que intentas actualizar no existe"));
        tipoEmail.setNombre(newTipoEmail.getNombre());
        return tipoEmailRepository.save(tipoEmail);
    }

    @Override
    public TipoEmail delete(int id) throws GlobalExceptions {
        TipoEmail tipoEmail = tipoEmailRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de email que intentas eliminar no existe"));
        tipoEmailRepository.delete(tipoEmail);
        return tipoEmail;
    }
}
