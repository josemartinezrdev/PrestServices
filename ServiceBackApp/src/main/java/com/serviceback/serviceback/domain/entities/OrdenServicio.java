package com.serviceback.serviceback.domain.entities;

import java.util.Date;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Entity
@Table(name = "ordenservicios")
public class OrdenServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int norden;

    @Column
    private Date fecha;

    @Column(length = 200)
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Persona cliente;

    @ManyToOne
    @JoinColumn(name = "idempleado")
    private Persona empleado;

    @ManyToOne
    private EstadoOrden estadoOrden;

    @OneToMany(mappedBy = "ordenServicio", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<DetalleOrden> detalles;

    @OneToMany(mappedBy = "ordenservicio", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<AprobacionServicio> aprobacionServicio;

    @OneToMany(mappedBy = "ordenServicio", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<OrdenTrabajo> ordenTrabajo;

    @PrePersist
    protected void onCreate() {
        fecha = new Date();
    }

    public OrdenServicio() {
    }

    public OrdenServicio(int norden, String motivo, Persona cliente, Persona empleado, EstadoOrden estadoOrden,
            List<DetalleOrden> detalles, List<AprobacionServicio> aprobacionServicio, List<OrdenTrabajo> ordenTrabajo) {
        this.norden = norden;
        this.motivo = motivo;
        this.cliente = cliente;
        this.empleado = empleado;
        this.estadoOrden = estadoOrden;
    }

    public int getNorden() {
        return this.norden;
    }

    public void setNorden(int norden) {
        this.norden = norden;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Persona getCliente() {
        return this.cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public Persona getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Persona empleado) {
        this.empleado = empleado;
    }

    public EstadoOrden getEstadoOrden() {
        return this.estadoOrden;
    }

    public void setEstadoOrden(EstadoOrden estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

}
