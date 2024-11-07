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

import com.serviceback.serviceback.application.services.IInsumo;
import com.serviceback.serviceback.domain.entities.Insumo;

@RestController
@RequestMapping("/api/insumos")
public class InsumoController {
    @Autowired
    private IInsumo service;

    @GetMapping
    public List<Insumo> list() {
        return service.findAll();
    }

    @GetMapping("/byservice/{idinsumo}")
    public List<Insumo> listByService(@PathVariable int idinsumo) {
        return service.findInsumosByService(idinsumo);
    }

    @GetMapping("/byproveedor/{nrodoc}")
    public List<Insumo> getInsumosByProveedor(@PathVariable String nrodoc) {
        return service.findInsumosByProveedor(nrodoc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Insumo insumo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(insumo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Insumo newInsumo) {
        return ResponseEntity.ok(service.update(id, newInsumo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
