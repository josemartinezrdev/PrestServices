package com.serviceback.serviceback.infrastructure.repositories.pedido;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("SELECT p FROM Pedido p WHERE p.transaccion.id = :id AND p.transaccion.estadoAprobacion.nombre = 'Pendiente'")
    List<Pedido> listByTransaccion(int id);

    @Query("SELECT p FROM Pedido p WHERE p.transaccion.id = :transaccionId")
    List<Pedido> findByTransaccionId(int transaccionId);

    
}
