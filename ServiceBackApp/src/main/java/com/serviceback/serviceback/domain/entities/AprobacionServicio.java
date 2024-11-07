package com.serviceback.serviceback.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "aprobacionservicios")
public class AprobacionServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "norden")
    private OrdenServicio ordenservicio;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Persona cliente;

    @ManyToOne
    @JoinColumn(name = "idservicio")
    private Servicio servicio;

    @Column
    private String hallazgo;
    @Column
    private String solucion;

    @ManyToOne
    @JoinColumn(name = "idestadoaprobacion")
    private EstadoAprobacion estadoaprobacion;

    public AprobacionServicio() {
    }

    public AprobacionServicio(int id, OrdenServicio ordenservicio, Persona cliente, Servicio servicio, String hallazgo,
            String solucion, EstadoAprobacion estadoaprobacion) {
        this.id = id;
        this.ordenservicio = ordenservicio;
        this.cliente = cliente;
        this.servicio = servicio;
        this.hallazgo = hallazgo;
        this.solucion = solucion;
        this.estadoaprobacion = estadoaprobacion;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrdenServicio getOrdenservicio() {
        return this.ordenservicio;
    }

    public void setOrdenservicio(OrdenServicio ordenservicio) {
        this.ordenservicio = ordenservicio;
    }

    public Persona getCliente() {
        return this.cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public Servicio getServicio() {
        return this.servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getHallazgo() {
        return this.hallazgo;
    }

    public void setHallazgo(String hallazgo) {
        this.hallazgo = hallazgo;
    }

    public String getSolucion() {
        return this.solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public EstadoAprobacion getEstadoaprobacion() {
        return this.estadoaprobacion;
    }

    public void setEstadoaprobacion(EstadoAprobacion estadoaprobacion) {
        this.estadoaprobacion = estadoaprobacion;
    }

}
