package com.serviceback.serviceback.infrastructure.repositories.ciudad;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.Ciudad;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad, Integer> {

}
