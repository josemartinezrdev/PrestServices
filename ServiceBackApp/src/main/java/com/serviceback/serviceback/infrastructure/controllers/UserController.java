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

import com.serviceback.serviceback.application.services.IUser;
import com.serviceback.serviceback.application.services.auth.AuthenticationService;
import com.serviceback.serviceback.domain.dtos.UserDto;
import com.serviceback.serviceback.domain.entities.security.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUser service;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public List<User> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.registerOneCustomer(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody User newUser) {
        return ResponseEntity.ok(service.update(id, newUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
