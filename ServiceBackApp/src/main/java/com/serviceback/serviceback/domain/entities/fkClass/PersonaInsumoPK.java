package com.serviceback.serviceback.domain.entities.fkClass;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PersonaInsumoPK implements Serializable {
    @Column(name = "idinsumo")
    private int idinsumo;
    @Column(name = "idservicio")
    private int idservicio;
    @Column(name = "nrodoc")
    private String nrodoc;

    public PersonaInsumoPK() {
    }

    public PersonaInsumoPK(int idinsumo, int idservicio, String nrodoc) {
        this.idinsumo = idinsumo;
        this.idservicio = idservicio;
        this.nrodoc = nrodoc;
    }

    public int getIdinsumo() {
        return this.idinsumo;
    }

    public void setIdinsumo(int idinsumo) {
        this.idinsumo = idinsumo;
    }

    public int getIdservicio() {
        return this.idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public String getNrodoc() {
        return this.nrodoc;
    }

    public void setNrodoc(String nrodoc) {
        this.nrodoc = nrodoc;
    }

}
