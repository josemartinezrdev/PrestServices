package com.serviceback.serviceback.domain.entities.security;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GrantedPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Rol role;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;

    public GrantedPermission() {
    }

    public GrantedPermission(int id, Rol role, Operation operation) {
        this.id = id;
        this.role = role;
        this.operation = operation;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rol getRole() {
        return this.role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }

    public Operation getOperation() {
        return this.operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

}
