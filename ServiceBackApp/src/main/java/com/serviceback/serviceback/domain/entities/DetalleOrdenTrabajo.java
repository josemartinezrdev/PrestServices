package com.serviceback.serviceback.domain.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "detallesordentrabajos")
public class DetalleOrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "idOrdenTrabajo")
    private OrdenTrabajo ordenTrabajo;

    @ManyToOne
    @JoinColumn(name = "idServicio")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "idempleado")
    private Persona empleado;

    @ManyToOne
    @JoinColumn(name = "idestado")
    private EstadoSerOrden estadoSerOrden;

    @PrePersist
    protected void onCreate() {
        fecha = new Date();
    }

    public DetalleOrdenTrabajo() {
    }

    public DetalleOrdenTrabajo(int id, Date fecha, OrdenTrabajo ordenTrabajo, Servicio servicio, Persona empleado,
            EstadoSerOrden estadoSerOrden) {
        this.id = id;
        this.fecha = fecha;
        this.ordenTrabajo = ordenTrabajo;
        this.servicio = servicio;
        this.empleado = empleado;
        this.estadoSerOrden = estadoSerOrden;
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

    public OrdenTrabajo getOrdenTrabajo() {
        return this.ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public Servicio getServicio() {
        return this.servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Persona getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Persona empleado) {
        this.empleado = empleado;
    }

    public EstadoSerOrden getEstadoSerOrden() {
        return this.estadoSerOrden;
    }

    public void setEstadoSerOrden(EstadoSerOrden estadoSerOrden) {
        this.estadoSerOrden = estadoSerOrden;
    }

}
