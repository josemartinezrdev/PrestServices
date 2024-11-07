package com.serviceback.serviceback.infrastructure.repositories.ordenservicio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.OrdenServicio;

@Repository
public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Integer> {

    List<OrdenServicio> findByEstadoOrden_Id(Integer estadoOrdenId);

}
