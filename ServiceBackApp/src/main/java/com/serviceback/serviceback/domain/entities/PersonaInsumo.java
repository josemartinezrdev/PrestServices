package com.serviceback.serviceback.domain.entities;

import com.serviceback.serviceback.domain.entities.fkClass.PersonaInsumoPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "personainsumos", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "idservicio", "idinsumo", "nrodoc" })
})
public class PersonaInsumo {
    @EmbeddedId
    private PersonaInsumoPK id;

    @ManyToOne()
    @JoinColumn(name = "idservicio", insertable = false, updatable = false)
    private Servicio servicio;

    @ManyToOne()
    @JoinColumn(name = "idinsumo", insertable = false, updatable = false)
    private Insumo insumo;

    @ManyToOne()
    @JoinColumn(name = "nrodoc", insertable = false, updatable = false)
    private Persona nrodoc;

    public PersonaInsumo() {
    }

    public PersonaInsumo(PersonaInsumoPK id, Servicio servicio, Insumo insumo, Persona nrodoc) {
        this.id = id;
        this.servicio = servicio;
        this.insumo = insumo;
        this.nrodoc = nrodoc;
    }

    public PersonaInsumoPK getId() {
        return this.id;
    }

    public void setId(PersonaInsumoPK id) {
        this.id = id;
    }

    public Servicio getServicio() {
        return this.servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Insumo getInsumo() {
        return this.insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Persona getNrodoc() {
        return this.nrodoc;
    }

    public void setNrodoc(Persona nrodoc) {
        this.nrodoc = nrodoc;
    }

}
