package com.serviceback.serviceback.domain.entities;

import com.serviceback.serviceback.domain.entities.fkClass.SucursalServicioPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sucursalservicio")
public class SucursalServicio {

    @EmbeddedId
    private SucursalServicioPK id;

    @Column(length = 10)
    private double valorservicio;

    @ManyToOne()
    @JoinColumn(name = "idsucursal", insertable = false, updatable = false)
    private Sucursal sucursal;

    @ManyToOne()
    @JoinColumn(name = "idservicio", insertable = false, updatable = false)
    private Servicio servicio;

    public SucursalServicio() {
    }

    public SucursalServicio(SucursalServicioPK id, double valorservicio, Sucursal sucursal, Servicio servicio) {
        this.id = id;
        this.valorservicio = valorservicio;
        this.sucursal = sucursal;
        this.servicio = servicio;
    }

    public SucursalServicioPK getId() {
        return this.id;
    }

    public void setId(SucursalServicioPK id) {
        this.id = id;
    }

    public double getValorservicio() {
        return this.valorservicio;
    }

    public void setValorservicio(double valorservicio) {
        this.valorservicio = valorservicio;
    }

    public Sucursal getSucursal() {
        return this.sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Servicio getServicio() {
        return this.servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

}
