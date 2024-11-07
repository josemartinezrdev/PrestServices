package com.serviceback.serviceback.infrastructure.repositories.estadoserorden;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.EstadoSerOrden;

@Repository
public interface EstadoSerOrdenRepository extends CrudRepository<EstadoSerOrden, Integer> {
    boolean existsByNombre(String nombre);
}
