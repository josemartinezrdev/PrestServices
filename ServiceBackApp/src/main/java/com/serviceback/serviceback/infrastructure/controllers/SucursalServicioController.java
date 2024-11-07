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

import com.serviceback.serviceback.application.services.ISucursalServicio;
import com.serviceback.serviceback.domain.entities.SucursalServicio;
import com.serviceback.serviceback.domain.entities.fkClass.SucursalServicioPK;

@RestController
@RequestMapping("/api/sucursalservicio")
public class SucursalServicioController {
    @Autowired
    private ISucursalServicio service;

    @GetMapping
    public List<SucursalServicio> list() {
        return service.findAll();
    }

    @GetMapping("/{idsucursal}/{idservicio}")
    public ResponseEntity<?> findById(@PathVariable int idsucursal, @PathVariable int idservicio) {
        SucursalServicioPK id = new SucursalServicioPK(idsucursal, idservicio);
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SucursalServicio sucursalservicio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(sucursalservicio));
    }

    @PutMapping("/{idsucursal}/{idservicio}")
    public ResponseEntity<?> update(@PathVariable int idsucursal, @PathVariable int idservicio,
            @RequestBody SucursalServicio newSucursalServicio) {
        SucursalServicioPK id = new SucursalServicioPK(idsucursal, idservicio);
        return ResponseEntity.ok(service.update(id, newSucursalServicio));
    }

    @DeleteMapping("/{idsucursal}/{idservicio}")
    public ResponseEntity<?> delete(@PathVariable int idsucursal, @PathVariable int idservicio) {
        SucursalServicioPK id = new SucursalServicioPK(idsucursal, idservicio);
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
