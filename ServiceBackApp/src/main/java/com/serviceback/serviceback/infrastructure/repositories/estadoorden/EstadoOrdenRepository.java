package com.serviceback.serviceback.infrastructure.repositories.estadoorden;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.EstadoOrden;

@Repository
public interface EstadoOrdenRepository extends CrudRepository<EstadoOrden, Integer> {
    boolean existsByNombre(String nombre);
}
