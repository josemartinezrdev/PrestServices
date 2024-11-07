package com.serviceback.serviceback.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.Date;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date fecha;

    @Column
    private double total;

    @ManyToOne
    private Persona persona;

    @ManyToOne
    private EstadoAprobacion estadoAprobacion;

    // esto es para los pedidos, se crea desde la bd un proveedor por defecto y es
    // el que se asigna para las compras de los clientes
    @ManyToOne
    @JoinColumn(name = "proveedor")
    private Persona proveedor;

    @OneToMany(mappedBy = "transaccion", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Pedido> pedidos;

    @PrePersist
    protected void onCreate() {
        fecha = new Date();
    }

    public Transaccion() {
    }

    public Transaccion(int id, Date fecha, double total, Persona persona, EstadoAprobacion estadoAprobacion,
            Persona proveedor, List<Pedido> pedidos) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.persona = persona;
        this.estadoAprobacion = estadoAprobacion;
        this.proveedor = proveedor;
        this.pedidos = pedidos;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public EstadoAprobacion getEstadoAprobacion() {
        return this.estadoAprobacion;
    }

    public void setEstadoAprobacion(EstadoAprobacion estadoAprobacion) {
        this.estadoAprobacion = estadoAprobacion;
    }

    public Persona getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(Persona proveedor) {
        this.proveedor = proveedor;
    }

    public List<Pedido> getPedidos() {
        return this.pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

}
