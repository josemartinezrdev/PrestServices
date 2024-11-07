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

import com.serviceback.serviceback.application.services.IOrdenServicio;
import com.serviceback.serviceback.domain.entities.OrdenServicio;

@RestController
@RequestMapping("/api/ordenservicios")
public class OrdenServicioController {

    @Autowired
    private IOrdenServicio service;

    @GetMapping
    public List<OrdenServicio> list() {
        return service.findAll();
    }

    @GetMapping("/{norden}")
    public ResponseEntity<?> findById(@PathVariable int norden) {
        return ResponseEntity.ok(service.findById(norden));
    }

    @GetMapping("/byestado/{id}")
    public ResponseEntity<?> findByEstadoOrden_Id(@PathVariable int id) {
        return ResponseEntity.ok(service.findByEstadoOrden_Id(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrdenServicio ordenservicio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(ordenservicio));
    }

    @PutMapping("/{norden}")
    public ResponseEntity<?> update(@PathVariable int norden, @RequestBody OrdenServicio newOrdenServicio) {
        return ResponseEntity.ok(service.update(norden, newOrdenServicio));
    }

    @DeleteMapping("/{norden}")
    public ResponseEntity<?> delete(@PathVariable int norden) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(norden));
    }
}
