package com.serviceback.serviceback.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int cantidad;

    @ManyToOne
    private Insumo insumo;

    @ManyToOne
    private Transaccion transaccion;

    @ManyToOne
    private EstadoAprobacion estadoAprobacion;

    public Pedido() {
    }

    public Pedido(int id, int cantidad, Insumo insumo, Transaccion transaccion, EstadoAprobacion estadoAprobacion) {
        this.id = id;
        this.cantidad = cantidad;
        this.insumo = insumo;
        this.transaccion = transaccion;
        this.estadoAprobacion = estadoAprobacion;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Insumo getInsumo() {
        return this.insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Transaccion getTransaccion() {
        return this.transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public EstadoAprobacion getEstadoAprobacion() {
        return this.estadoAprobacion;
    }

    public void setEstadoAprobacion(EstadoAprobacion estadoAprobacion) {
        this.estadoAprobacion = estadoAprobacion;
    }

}
