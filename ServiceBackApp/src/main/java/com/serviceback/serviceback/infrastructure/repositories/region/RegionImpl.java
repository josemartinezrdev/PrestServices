package com.serviceback.serviceback.infrastructure.repositories.region;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IRegion;
import com.serviceback.serviceback.domain.entities.Region;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class RegionImpl implements IRegion {

    @Autowired
    private RegionRepository regionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Region> findAll() throws GlobalExceptions {
        List<Region> regiones = (List<Region>) regionRepository.findAll();
        if (regiones.isEmpty()) {
            throw new GlobalExceptions("No se encontraron regiones.");
        }
        return regiones;
    }

    @Transactional(readOnly = true)
    @Override
    public Region findById(int id) throws GlobalExceptions {
        return regionRepository.findById(id).orElseThrow(() -> new GlobalExceptions("La Region no existe"));

    }

    @Transactional
    @Override
    public Region save(Region region) throws GlobalExceptions {
        if (region == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return regionRepository.save(region);
    }

    @Transactional
    @Override
    public Region update(int id, Region region) throws GlobalExceptions {
        Region newRegion = regionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La Region que intentas actualizar no existe"));
        newRegion.setNombre(region.getNombre());
        newRegion.setPais(region.getPais());
        return regionRepository.save(newRegion);
    }

    @Transactional
    @Override
    public Region delete(int id) throws GlobalExceptions {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("La region que intentas eliminar no existe"));
        regionRepository.delete(region);
        return region;
    }

}
