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

import com.serviceback.serviceback.application.services.IEstadoOrden;
import com.serviceback.serviceback.domain.entities.EstadoOrden;


@RestController
@RequestMapping("/api/estadoordenes")
public class EstadoOrdenController {

    @Autowired
    private IEstadoOrden service;

    @GetMapping
    public List<EstadoOrden> List() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable int id) {
            return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EstadoOrden estadoorden) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estadoorden));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody EstadoOrden estadoorden) {
        return ResponseEntity.ok(service.update(id, estadoorden));
    }
}
