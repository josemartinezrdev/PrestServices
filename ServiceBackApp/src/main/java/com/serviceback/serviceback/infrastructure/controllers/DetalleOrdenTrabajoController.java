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

import com.serviceback.serviceback.application.services.IDetalleOrdenTrabajo;
import com.serviceback.serviceback.domain.entities.DetalleOrdenTrabajo;

@RestController
@RequestMapping("/api/detalleordentrabajos")
public class DetalleOrdenTrabajoController {

    @Autowired
    private IDetalleOrdenTrabajo service;

    @GetMapping
    public List<DetalleOrdenTrabajo> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/byprofesional/{nrodoc}/{idestado}")
    public ResponseEntity<?> findByEmpleadoNrodoc(@PathVariable String nrodoc, @PathVariable int idestado) {
        return ResponseEntity.ok(service.findByEmpleadoNrodoc(nrodoc, idestado));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DetalleOrdenTrabajo detalleordentrabajo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(detalleordentrabajo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody DetalleOrdenTrabajo newDetalleOrdenTrabajo) {
        return ResponseEntity.ok(service.update(id, newDetalleOrdenTrabajo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
