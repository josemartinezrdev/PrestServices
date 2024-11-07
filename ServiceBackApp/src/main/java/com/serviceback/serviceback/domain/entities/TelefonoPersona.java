package com.serviceback.serviceback.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "telpersonas")
public class TelefonoPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 10)
    private String nro;

    @ManyToOne
    private TipoTelefono tipoTelefono;

    @ManyToOne
    private Persona persona;

    public TelefonoPersona() {
    }

    public TelefonoPersona(int id, String nro, TipoTelefono tipoTelefono, Persona persona) {
        this.id = id;
        this.nro = nro;
        this.tipoTelefono = tipoTelefono;
        this.persona = persona;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNro() {
        return this.nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public TipoTelefono getTipoTelefono() {
        return this.tipoTelefono;
    }

    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
