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

import com.serviceback.serviceback.application.services.ITelefonoSucursal;
import com.serviceback.serviceback.domain.entities.TelefonoSucursal;

@RestController
@RequestMapping("/api/telsucursales")
public class TelefonoSucursalController {
    @Autowired
    private ITelefonoSucursal service;

    @GetMapping
    public List<TelefonoSucursal> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TelefonoSucursal telefonoSucursal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(telefonoSucursal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody TelefonoSucursal newTelefonoSucursal) {
        return ResponseEntity.ok(service.update(id, newTelefonoSucursal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
