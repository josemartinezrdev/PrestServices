package com.serviceback.serviceback.domain.entities.fkClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRolPK implements Serializable {

    @Column(name = "iduser")
    private String iduser;
    @Column(name = "idrol")
    private int idrol;

    public UserRolPK() {
    }

    public UserRolPK(String iduser, int idrol) {
        this.iduser = iduser;
        this.idrol = idrol;
    }

    public String getIduser() {
        return this.iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public int getIdrol() {
        return this.idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

}
