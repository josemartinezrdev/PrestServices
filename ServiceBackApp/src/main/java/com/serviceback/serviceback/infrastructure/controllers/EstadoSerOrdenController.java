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

import com.serviceback.serviceback.application.services.IEstadoSerOrden;
import com.serviceback.serviceback.domain.entities.EstadoSerOrden;

@RestController
@RequestMapping("/api/estadoserordenes")
public class EstadoSerOrdenController {

    @Autowired
    private IEstadoSerOrden service;

    @GetMapping
    public List<EstadoSerOrden> List() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable int id) {
            return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EstadoSerOrden estadoserorden) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estadoserorden));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody EstadoSerOrden estadoserorden) {
        return ResponseEntity.ok(service.update(id, estadoserorden));
    }
}
