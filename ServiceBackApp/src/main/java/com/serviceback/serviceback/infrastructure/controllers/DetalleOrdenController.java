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

import com.serviceback.serviceback.application.services.IDetalleOrden;
import com.serviceback.serviceback.domain.entities.DetalleOrden;
@RestController
@RequestMapping("/api/detalleordenes")
public class DetalleOrdenController {
    @Autowired
    private IDetalleOrden service;

    @GetMapping
    public List<DetalleOrden> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DetalleOrden detalleorden) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(detalleorden));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody DetalleOrden newDetalleOrden) {
        return ResponseEntity.ok(service.update(id, newDetalleOrden));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}