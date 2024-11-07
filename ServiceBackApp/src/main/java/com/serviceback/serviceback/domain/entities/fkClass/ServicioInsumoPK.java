package com.serviceback.serviceback.domain.entities.fkClass;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ServicioInsumoPK implements Serializable {

    @Column(name = "idservicio")
    private int idservicio;
    @Column(name = "idinsumo")
    private int idinsumo;

    public ServicioInsumoPK() {
    }

    public ServicioInsumoPK(int idservicio, int idinsumo) {
        this.idservicio = idservicio;
        this.idinsumo = idinsumo;
    }

    public int getIdservicio() {
        return this.idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public int getIdinsumo() {
        return this.idinsumo;
    }

    public void setIdinsumo(int idinsumo) {
        this.idinsumo = idinsumo;
    }

}
