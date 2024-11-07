package com.serviceback.serviceback.infrastructure.repositories.rol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IRol;
import com.serviceback.serviceback.domain.entities.security.Rol;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class RolImp implements IRol {
    @Autowired
    private RolRepository rolRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Rol> findAll() {
        List<Rol> rols = rolRepository.findAll();
        if (rols.isEmpty()) {
            throw new GlobalExceptions("No se encontraron roles.");
        }
        return rols;
    }

    @Transactional(readOnly = true)
    @Override
    public Rol findById(int id) throws GlobalExceptions {
        return rolRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El rol que intentas buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Rol save(Rol rol) throws GlobalExceptions {
        if (rol == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        if (rolRepository.existsByNombre(rol.getNombre())) {
            throw new GlobalExceptions("El valor ya existe");
        }

        return rolRepository.save(rol);
    }

    @Override
    public Rol update(int id, Rol newRol) throws GlobalExceptions {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El rol que intentas actualizar no existe"));
        rol.setNombre(newRol.getNombre());
        return rolRepository.save(rol);
    }

    @Override
    public Rol delete(int id) throws GlobalExceptions {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El rol que intentas eliminar no existe"));
        rolRepository.delete(rol);
        return rol;
    }

    @Override
    public Rol findByNombre(String rol) {
        return rolRepository.findByNombre(rol);
    }
}
