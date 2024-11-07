package com.serviceback.serviceback.infrastructure.repositories.tipoempresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.ITipoEmpresa;
import com.serviceback.serviceback.domain.entities.TipoEmpresa;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class TipoEmpresaImp implements ITipoEmpresa {

    @Autowired
    private TipoEmpresaRepository tipoEmpresaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoEmpresa> findAll() {
        List<TipoEmpresa> tipoEmpresas = tipoEmpresaRepository.findAll();
        if (tipoEmpresas.isEmpty()) {
            throw new GlobalExceptions("No se encontraron tipos de empresa.");
        }
        return tipoEmpresas;
    }

    @Transactional(readOnly = true)
    @Override
    public TipoEmpresa findById(int id) throws GlobalExceptions {
        return tipoEmpresaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de empresa que intentas buscar no existe"));
    }

    @Override
    public TipoEmpresa save(TipoEmpresa tipoEmpresa) throws GlobalExceptions {
        if (tipoEmpresa == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return tipoEmpresaRepository.save(tipoEmpresa);
    }

    @Override
    public TipoEmpresa update(int id, TipoEmpresa newTipoEmpresa) throws GlobalExceptions {
        TipoEmpresa tipoEmpresa = tipoEmpresaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de empresa que intentas actualizar no existe"));
        tipoEmpresa.setDescripcion(newTipoEmpresa.getDescripcion());
        return tipoEmpresaRepository.save(tipoEmpresa);
    }

    @Override
    public TipoEmpresa delete(int id) throws GlobalExceptions {
        TipoEmpresa tipoEmpresa = tipoEmpresaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El tipo de empresa que intentas eliminar no existe"));
        tipoEmpresaRepository.delete(tipoEmpresa);
        return tipoEmpresa;
    }

}
