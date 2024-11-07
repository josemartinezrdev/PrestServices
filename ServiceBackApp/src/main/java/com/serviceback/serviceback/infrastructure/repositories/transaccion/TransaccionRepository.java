package com.serviceback.serviceback.infrastructure.repositories.transaccion;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.Transaccion;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
    @Query("SELECT t FROM Transaccion t " +
            "WHERE t.proveedor.nrodoc = :proveedorId " +
            "AND t.estadoAprobacion.id = :estadoId")
    List<Transaccion> findByProveedorIdAndEstadoId(String proveedorId, int estadoId);

    @Query("""
            SELECT t FROM Transaccion t
            JOIN t.pedidos p
            JOIN ServicioInsumo si ON si.insumo = p.insumo
            JOIN si.servicio s
            WHERE s.id = :idservicio
            """)
    List<Transaccion> findTransaccionByIdServicio(int idservicio);

}
