package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.Region;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IRegion {

    List<Region> findAll();

    Region findById(int id);

    Region update(int id, Region region) throws GlobalExceptions;

    Region save(Region region) throws GlobalExceptions;

    Region delete(int id);
   
}
