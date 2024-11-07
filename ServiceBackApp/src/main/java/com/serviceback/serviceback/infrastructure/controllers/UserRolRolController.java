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

import com.serviceback.serviceback.application.services.IUserRol;
import com.serviceback.serviceback.domain.entities.UserRol;
import com.serviceback.serviceback.domain.entities.fkClass.UserRolPK;

@RestController
@RequestMapping("/api/userroles")
public class UserRolRolController {
    @Autowired
    private IUserRol service;

    @GetMapping
    public List<UserRol> list() {
        return service.findAll();
    }

    @GetMapping("/{iduser}/{idrol}")
    public ResponseEntity<?> findById(@PathVariable String iduser, @PathVariable int idrol) {
        UserRolPK id = new UserRolPK(iduser, idrol);
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserRol userrol) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userrol));
    }

    @PutMapping("/{iduser}/{idrol}")
    public ResponseEntity<?> update(@PathVariable String iduser, @PathVariable int idrol,
            @RequestBody UserRol newUserRol) {
        UserRolPK id = new UserRolPK(iduser, idrol);
        return ResponseEntity.ok(service.update(id, newUserRol));
    }

    @DeleteMapping("/{iduser}/{idrol}")
    public ResponseEntity<?> delete(@PathVariable String iduser, @PathVariable int idrol) {
        UserRolPK id = new UserRolPK(iduser, idrol);
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
