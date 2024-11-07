package com.serviceback.serviceback.domain.entities;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordentrabajos")
public class OrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nrotrabajo;

    @Column
    private Date fechaasignacion;
    @Column
    private Time horaasignacion;

    @ManyToOne
    @JoinColumn(name = "idempleado")
    private Persona empleado;

    @ManyToOne
    @JoinColumn(name = "norden")
    private OrdenServicio ordenServicio;

    @OneToMany(mappedBy = "ordenTrabajo", cascade = CascadeType.REMOVE)
    private List<DetalleOrdenTrabajo> detOrdenTrabajos;

    public OrdenTrabajo() {
    }

    public OrdenTrabajo(int id, String nrotrabajo, Date fechaasignacion, Time horaasignacion, Persona empleado,
            OrdenServicio ordenServicio) {
        this.id = id;
        this.nrotrabajo = nrotrabajo;
        this.fechaasignacion = fechaasignacion;
        this.horaasignacion = horaasignacion;
        this.empleado = empleado;
        this.ordenServicio = ordenServicio;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNrotrabajo() {
        return this.nrotrabajo;
    }

    public void setNrotrabajo(String nrotrabajo) {
        this.nrotrabajo = nrotrabajo;
    }

    public Date getFechaasignacion() {
        return this.fechaasignacion;
    }

    public void setFechaasignacion(Date fechaasignacion) {
        this.fechaasignacion = fechaasignacion;
    }

    public Time getHoraasignacion() {
        return this.horaasignacion;
    }

    public void setHoraasignacion(Time horaasignacion) {
        this.horaasignacion = horaasignacion;
    }

    public Persona getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Persona empleado) {
        this.empleado = empleado;
    }

    public OrdenServicio getOrdenServicio() {
        return this.ordenServicio;
    }

    public void setOrdenServicio(OrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

}
