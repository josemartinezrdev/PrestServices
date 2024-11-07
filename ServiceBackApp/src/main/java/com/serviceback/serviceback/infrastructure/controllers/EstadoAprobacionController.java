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

import com.serviceback.serviceback.application.services.IEstadoAprobacion;
import com.serviceback.serviceback.domain.entities.EstadoAprobacion;

@RestController
@RequestMapping("/api/estadoaprobaciones")
public class EstadoAprobacionController {

    @Autowired
    private IEstadoAprobacion service;

    @GetMapping
    public List<EstadoAprobacion> List() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable int id) {
            return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EstadoAprobacion estadoaprobacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estadoaprobacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody EstadoAprobacion estadoaprobacion) {
        return ResponseEntity.ok(service.update(id, estadoaprobacion));
    }
}
