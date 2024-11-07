package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.Pedido;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IPedido {
    List<Pedido> listByTransaccion(int id);

    List<Pedido> findAll();

    List<Pedido> findByTransaccionId(int transaccionId);

    Pedido findById(int id) throws GlobalExceptions;

    Pedido save(Pedido pedido) throws GlobalExceptions;

    Pedido update(int id, Pedido pedido);

    Pedido delete(int id);
}
