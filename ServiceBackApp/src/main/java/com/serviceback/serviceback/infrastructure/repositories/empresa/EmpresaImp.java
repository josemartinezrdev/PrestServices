package com.serviceback.serviceback.infrastructure.repositories.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IEmpresa;
import com.serviceback.serviceback.domain.entities.Empresa;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class EmpresaImp implements IEmpresa {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Empresa> findAll() {
        List<Empresa> empresas = empresaRepository.findAll();
        if (empresas.isEmpty()) {
            throw new GlobalExceptions("No se encontraron empresas.");
        }
        return empresas;
    }

    @Transactional(readOnly = true)
    @Override
    public Empresa findById(int id) throws GlobalExceptions {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La empresa que intentas buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Empresa save(Empresa empresa) throws GlobalExceptions {
        if (empresa == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        if (empresaRepository.existsByNombre(empresa.getNombre())) {
            throw new GlobalExceptions("El valor ya existe");
        }
        return empresaRepository.save(empresa);
    }

    @Transactional
    @Override
    public Empresa update(int id, Empresa newEmpresa) throws GlobalExceptions {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La empresa que intentas actualizar no existe"));
        empresa.setNombre(newEmpresa.getNombre());
        empresa.setTipoEmpresa(newEmpresa.getTipoEmpresa());
        return empresaRepository.save(empresa);
    }

    @Transactional
    @Override
    public Empresa delete(int id) throws GlobalExceptions {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La empresa que intentas eliminar no existe"));
        empresaRepository.delete(empresa);
        return empresa;
    }

}
