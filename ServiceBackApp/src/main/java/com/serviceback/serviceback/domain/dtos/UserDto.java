package com.serviceback.serviceback.domain.dtos;

import java.io.Serializable;

public class UserDto implements Serializable {

    private String nombre;
    private String username;
    private String password;
    private String repeatedPassword;
    private String rol;

    public UserDto() {
    }

    public UserDto(String nombre, String username, String password, String repeatedPassword, String rol) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.rol = rol;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return this.repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
