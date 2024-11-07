package com.serviceback.serviceback.infrastructure.repositories.aprobacionservicio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.AprobacionServicio;
import com.serviceback.serviceback.domain.entities.Servicio;

@Repository
public interface AprobacionServicioRepository extends JpaRepository<AprobacionServicio, Integer> {
    @Query("SELECT a FROM AprobacionServicio a WHERE a.cliente.nrodoc = :nrodoc AND a.estadoaprobacion.id = :idestado")
    List<AprobacionServicio> findByCliente(String nrodoc, int idestado);

    @Query("SELECT s FROM Servicio s " +
            "JOIN DetalleOrden d ON d.servicio.id = s.id " +
            "JOIN OrdenServicio o ON o.norden = d.ordenServicio.norden " +
            "WHERE o.norden = :norden")
    Servicio findAprobacionesWithServiceByNorden(@Param("norden") int norden);

}
