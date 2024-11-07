package com.serviceback.serviceback.infrastructure.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceback.serviceback.application.services.IPedido;
import com.serviceback.serviceback.domain.entities.Pedido;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private IPedido service;

    @GetMapping("/transacciones/{id}")
    public List<Pedido> listByTransaccion(@PathVariable int id) {
        return service.listByTransaccion(id);
    }

    @GetMapping("/bytransaccion/{transaccionId}")
    public List<Pedido> obtenerPedidosPorTransaccion(@PathVariable int transaccionId) {
        return service.findByTransaccionId(transaccionId);
    }

    @GetMapping
    public List<Pedido> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Pedido newPedido) {
        return ResponseEntity.ok(service.update(id, newPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
