package com.serviceback.serviceback.infrastructure.repositories.detalleorden;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceback.serviceback.domain.entities.DetalleOrden;

public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer > {

}
