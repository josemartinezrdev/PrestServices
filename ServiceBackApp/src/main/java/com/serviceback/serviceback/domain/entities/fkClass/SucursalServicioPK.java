package com.serviceback.serviceback.domain.entities.fkClass;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SucursalServicioPK implements Serializable {

    @Column(name = "idsucursal")
    private int idsucursal;
    @Column(name = "idservicio")
    private int idservicio;

    public SucursalServicioPK() {
    }

    public SucursalServicioPK(int idsucursal, int idservicio) {
        this.idsucursal = idsucursal;
        this.idservicio = idservicio;
    }

    public int getIdsucursal() {
        return this.idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

    public int getIdservicio() {
        return this.idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

}
