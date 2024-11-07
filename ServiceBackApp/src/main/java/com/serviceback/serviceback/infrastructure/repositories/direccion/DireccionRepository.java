package com.serviceback.serviceback.infrastructure.repositories.direccion;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.Direccion;

@Repository
public interface DireccionRepository extends CrudRepository<Direccion, Integer> {

}
