package com.serviceback.serviceback.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "telsucursales")
public class TelefonoSucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 40)
    private String telefono;

    @ManyToOne
    private Sucursal sucursal;
    @ManyToOne
    private TipoTelefono tipoTelefono;

    public TelefonoSucursal() {
    }

    public TelefonoSucursal(int id, String telefono, Sucursal sucursal, TipoTelefono tipoTelefono) {
        this.id = id;
        this.telefono = telefono;
        this.sucursal = sucursal;
        this.tipoTelefono = tipoTelefono;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Sucursal getSucursal() {
        return this.sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public TipoTelefono getTipoTelefono() {
        return this.tipoTelefono;
    }

    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

}
