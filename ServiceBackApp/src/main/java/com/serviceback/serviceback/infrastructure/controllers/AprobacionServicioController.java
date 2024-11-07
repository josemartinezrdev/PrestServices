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

import com.serviceback.serviceback.application.services.IAprobacionServicio;
import com.serviceback.serviceback.domain.entities.AprobacionServicio;
import com.serviceback.serviceback.domain.entities.Servicio;

@RestController
@RequestMapping("/api/aprobacionservicios")
public class AprobacionServicioController {

    @Autowired
    private IAprobacionServicio service;

    @GetMapping
    public List<AprobacionServicio> list() {
        return service.findAll();
    }

    @GetMapping("/persona/{nrodoc}/{idestado}")
    public List<AprobacionServicio> listByPerson(@PathVariable String nrodoc, @PathVariable int idestado) {
        return service.findByCliente(nrodoc, idestado);
    }

    @GetMapping("/byordenservicio/{norden}")
    public Servicio findAprobacionesWithServiceByNorden(@PathVariable int norden) {
        return service.findAprobacionesWithServiceByNorden(norden);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AprobacionServicio aprobacionservicio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(aprobacionservicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody AprobacionServicio newAprobacionServicio) {
        return ResponseEntity.ok(service.update(id, newAprobacionServicio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
