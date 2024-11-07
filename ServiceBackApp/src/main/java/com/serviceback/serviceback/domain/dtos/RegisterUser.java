package com.serviceback.serviceback.domain.dtos;

import java.io.Serializable;

public class RegisterUser implements Serializable {
    private String nombre;
    private String username;
    private String role;
    private String jwt;

    public RegisterUser() {
    }

    public RegisterUser(String nombre, String username, String role, String jwt) {
        this.nombre = nombre;
        this.username = username;
        this.role = role;
        this.jwt = jwt;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
