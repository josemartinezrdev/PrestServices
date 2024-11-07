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

import com.serviceback.serviceback.application.services.IPersona;
import com.serviceback.serviceback.application.services.IUser;
import com.serviceback.serviceback.domain.entities.Persona;
import com.serviceback.serviceback.domain.entities.security.User;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    @Autowired
    private IPersona service;

    @Autowired
    private IUser userService;

    @GetMapping
    public List<Persona> list() {
        return service.findAll();
    }

    @GetMapping("/{nrodoc}")
    public ResponseEntity<?> findById(@PathVariable String nrodoc) {
        return ResponseEntity.ok(service.findById(nrodoc));
    }

    @GetMapping("/byusername/{username}")
    public ResponseEntity<?> findPersonaByUsername(@PathVariable String username) {
        return ResponseEntity.ok(service.findPersonaByUsername(username));
    }

    @GetMapping("/byrole/{id}")
    public ResponseEntity<?> findByRole(@PathVariable int id) {
        return ResponseEntity.ok(service.findByRole(id));
    }

    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        User user = userService.findById(persona.getUser().getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        persona.setUser(user);
        Persona savedPersona = service.save(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersona);
    }

    @PutMapping("/{nrodoc}")
    public ResponseEntity<?> update(@PathVariable String nrodoc, @RequestBody Persona newPersona) {
        return ResponseEntity.ok(service.update(nrodoc, newPersona));
    }

    @DeleteMapping("/{nrodoc}")
    public ResponseEntity<?> delete(@PathVariable String nrodoc) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(nrodoc));
    }
}
