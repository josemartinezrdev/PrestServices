package com.serviceback.serviceback.infrastructure.repositories.estadoarprobacion;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.EstadoAprobacion;

@Repository
public interface EstadoAprobacionRepository extends CrudRepository<EstadoAprobacion, Integer> {
    boolean existsByNombre(String nombre);
}
