package com.serviceback.serviceback.infrastructure.repositories.detalleordentrabajo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.DetalleOrdenTrabajo;

@Repository
public interface DetalleOrdenTrabajoRepository extends JpaRepository<DetalleOrdenTrabajo, Integer> {
    List<DetalleOrdenTrabajo> findByEmpleadoNrodocAndEstadoSerOrdenId(String nrodoc, int estadoId);
}
