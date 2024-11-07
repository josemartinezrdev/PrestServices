package com.serviceback.serviceback.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalleordenes")
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "nro_ordenservicio")
    private OrdenServicio ordenServicio;

    @ManyToOne
    private Servicio servicio;

    public DetalleOrden() {
    }

    public DetalleOrden(int id, OrdenServicio ordenServicio, Servicio servicio) {
        this.id = id;
        this.ordenServicio = ordenServicio;
        this.servicio = servicio;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrdenServicio getOrdenServicio() {
        return this.ordenServicio;
    }

    public void setOrdenServicio(OrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public Servicio getServicio() {
        return this.servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

}
