package com.serviceback.serviceback.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.serviceback.serviceback.application.services.IPersonaInsumo;
import com.serviceback.serviceback.domain.entities.PersonaInsumo;
import com.serviceback.serviceback.domain.entities.fkClass.PersonaInsumoPK;

@RestController
@RequestMapping("/api/personainsumos")
public class PersonaInsumoController {
    @Autowired
    private IPersonaInsumo service;

    @GetMapping
    public List<PersonaInsumo> list() {
        return service.findAll();
    }

    @GetMapping("/{idinsumo}/{idservicio}/{nrodoc}")
    public ResponseEntity<?> findById(@PathVariable int idinsumo, @PathVariable int idservicio,
            @PathVariable String nrodoc) {
        PersonaInsumoPK id = new PersonaInsumoPK(idinsumo, idservicio, nrodoc);
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PersonaInsumo personainsumo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(personainsumo));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El registro ya existe.");
        }
    }

    @PutMapping("/{idinsumo}/{idservicio}/{nrodoc}")
    public ResponseEntity<?> update(@PathVariable int idinsumo, @PathVariable int idservicio,
            @PathVariable String nrodoc,
            @RequestBody PersonaInsumo newPersonaInsumo) {
        PersonaInsumoPK id = new PersonaInsumoPK(idinsumo, idservicio, nrodoc);
        return ResponseEntity.ok(service.update(id, newPersonaInsumo));
    }

    @DeleteMapping("/{idinsumo}/{idservicio}/{nrodoc}")
    public ResponseEntity<?> delete(@PathVariable int idinsumo, @PathVariable int idservicio,
            @PathVariable String nrodoc) {
        PersonaInsumoPK id = new PersonaInsumoPK(idinsumo, idservicio, nrodoc);
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
