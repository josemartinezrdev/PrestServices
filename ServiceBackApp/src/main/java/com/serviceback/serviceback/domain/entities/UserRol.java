package com.serviceback.serviceback.domain.entities;

import com.serviceback.serviceback.domain.entities.fkClass.UserRolPK;
import com.serviceback.serviceback.domain.entities.security.Rol;
import com.serviceback.serviceback.domain.entities.security.User;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "userroles")
public class UserRol {
    @EmbeddedId
    private UserRolPK id;

    @ManyToOne()
    @JoinColumn(name = "iduser", insertable = false, updatable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "idrol", insertable = false, updatable = false)
    private Rol rol;

    public UserRol() {
    }

    public UserRol(UserRolPK id, User user, Rol rol) {
        this.id = id;
        this.user = user;
        this.rol = rol;
    }

    public UserRolPK getId() {
        return this.id;
    }

    public void setId(UserRolPK id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
