package com.serviceback.serviceback.infrastructure.repositories.pais;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.Pais;

@Repository
public interface PaisRepository extends CrudRepository<Pais, Integer> {

}
