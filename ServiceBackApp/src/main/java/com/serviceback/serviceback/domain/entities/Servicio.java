package com.serviceback.serviceback.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicios")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 40, unique = true)
    private String nombre;

    @Column(length = 100)
    private String descripcion;

    @Column
    private double valorservicio;

    @Column
    private boolean requiereinsumo;

    @PrePersist
    protected void onCreate() {
        requiereinsumo = false;
    }

    public Servicio() {
    }

    public Servicio(int id, String nombre, String descripcion, double valorservicio, boolean requiereinsumo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorservicio = valorservicio;
        this.requiereinsumo = requiereinsumo;
    }

    public double getValorservicio() {
        return this.valorservicio;
    }

    public void setValorservicio(double valorservicio) {
        this.valorservicio = valorservicio;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isRequiereinsumo() {
        return this.requiereinsumo;
    }

    public boolean getRequiereinsumo() {
        return this.requiereinsumo;
    }

    public void setRequiereinsumo(boolean requiereinsumo) {
        this.requiereinsumo = requiereinsumo;
    }

}
