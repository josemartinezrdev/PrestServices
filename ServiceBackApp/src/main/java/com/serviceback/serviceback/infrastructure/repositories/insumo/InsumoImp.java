package com.serviceback.serviceback.infrastructure.repositories.insumo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IInsumo;
import com.serviceback.serviceback.domain.entities.Insumo;
import com.serviceback.serviceback.infrastructure.repositories.personainsumo.PersonaInsumoRepository;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class InsumoImp implements IInsumo {
    @Autowired
    private InsumoRepository insumoRepository;

     @Autowired
    private PersonaInsumoRepository personaInsumoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Insumo> findAll() {
        List<Insumo> insumos = insumoRepository.findAll();
        if (insumos.isEmpty()) {
            throw new GlobalExceptions("No se encontraron insumos.");
        }
        return insumos;
    }

    @Transactional(readOnly = true)
    @Override
    public Insumo findById(int id) throws GlobalExceptions {
        return insumoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El insumo que intenta buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Insumo save(Insumo insumo) throws GlobalExceptions {
        if (insumo == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        if (insumoRepository.existsByNombre(insumo.getNombre())) {
            throw new GlobalExceptions("El valor ya existe: " + insumo.getNombre());
        }
        return insumoRepository.save(insumo);
    }

    @Transactional
    @Override
    public Insumo update(int id, Insumo newInsumo) throws GlobalExceptions {
        Insumo insumo = insumoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El insumo que intenta actualizar no existe"));
        insumo.setCodinterno(newInsumo.getCodinterno());
        insumo.setNombre(newInsumo.getNombre());
        insumo.setValorunitario(newInsumo.getValorunitario());
        insumo.setStock(newInsumo.getStock());
        insumo.setStockmin(newInsumo.getStockmin());
        insumo.setStockmax(newInsumo.getStockmax());
        return insumoRepository.save(insumo);
    }

    @Transactional
    @Override
    public Insumo delete(int id) throws GlobalExceptions {
        Insumo insumo = insumoRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El insumo que intenta eliminar no existe"));
        insumoRepository.delete(insumo);
        return insumo;
    }

    @Transactional
    @Override
    public List<Insumo> findInsumosByService(int idinsumo) {
        List<Insumo> insumosByServices = insumoRepository.findInsumosByService(idinsumo);
        if (insumosByServices.isEmpty()) {
            throw new GlobalExceptions("El Servicio no necesita insumos o no se encontraron.");
        }
        return insumosByServices;
    }

    @Override
    public List<Insumo> findInsumosByProveedor(String nrodoc) {
        return personaInsumoRepository.findInsumosByProveedor(nrodoc);
    }
}
