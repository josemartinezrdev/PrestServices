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

import com.serviceback.serviceback.application.services.IServicioInsumo;
import com.serviceback.serviceback.domain.entities.ServicioInsumo;
import com.serviceback.serviceback.domain.entities.fkClass.ServicioInsumoPK;

@RestController
@RequestMapping("/api/servicioinsumos")
public class ServicioInsumoController {
    @Autowired
    private IServicioInsumo service;

    @GetMapping
    public List<ServicioInsumo> list() {
        return service.findAll();
    }

    @GetMapping("/{idservicio}/{idinsumo}")
    public ResponseEntity<?> findById(@PathVariable int idservicio, @PathVariable int idinsumo) {
        ServicioInsumoPK id = new ServicioInsumoPK(idservicio, idinsumo);
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ServicioInsumo servicioinsumo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(servicioinsumo));
    }

    @PutMapping("/{idservicio}/{idinsumo}")
    public ResponseEntity<?> update(@PathVariable int idservicio, @PathVariable int idinsumo,
            @RequestBody ServicioInsumo newServicioInsumo) {
        ServicioInsumoPK id = new ServicioInsumoPK(idservicio, idinsumo);
        return ResponseEntity.ok(service.update(id, newServicioInsumo));
    }

    @DeleteMapping("/{idservicio}/{idinsumo}")
    public ResponseEntity<?> delete(@PathVariable int idservicio, @PathVariable int idinsumo) {
        ServicioInsumoPK id = new ServicioInsumoPK(idservicio, idinsumo);
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
