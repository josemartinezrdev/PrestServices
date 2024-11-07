package com.serviceback.serviceback.infrastructure.repositories.region;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.Region;


@Repository
public interface RegionRepository extends CrudRepository<Region, Integer> {

}
