package com.serviceback.serviceback.infrastructure.repositories.sucursalservicio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.SucursalServicio;
import com.serviceback.serviceback.domain.entities.fkClass.SucursalServicioPK;

@Repository
public interface SucursalServicioRepository extends JpaRepository<SucursalServicio, SucursalServicioPK> {

}
