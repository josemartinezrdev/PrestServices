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

import com.serviceback.serviceback.application.services.ITransaccion;
import com.serviceback.serviceback.domain.entities.Transaccion;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {
    @Autowired
    private ITransaccion service;

    @GetMapping
    public List<Transaccion> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/byrole/{idproveedor}/{estadoId}")
    public List<Transaccion> obtenerTransaccionesPorRol(@PathVariable String idproveedor, @PathVariable int estadoId) {
        return service.findByProveedorIdAndEstadoId(idproveedor, estadoId);
    }

    @GetMapping("/byservicio/{idservicio}")
    public List<Transaccion> findTransaccionByIdServicio(@PathVariable int idservicio) {
        return service.findTransaccionByIdServicio(idservicio);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Transaccion transaccion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(transaccion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Transaccion newTransaccion) {
        return ResponseEntity.ok(service.update(id, newTransaccion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}