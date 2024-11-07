package com.serviceback.serviceback.domain.entities;

import com.serviceback.serviceback.domain.entities.fkClass.ServicioInsumoPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicioinsumos")
public class ServicioInsumo {
    @EmbeddedId
    private ServicioInsumoPK id;

    @Column
    private int cantidadinsumo;

    @Column(length = 10)
    private Double valortotal;

    @ManyToOne()
    @JoinColumn(name = "idservicio", insertable = false, updatable = false)
    private Servicio servicio;

    @ManyToOne()
    @JoinColumn(name = "idinsumo", insertable = false, updatable = false)
    private Insumo insumo;

    @PrePersist
    protected void onCreate() {
        this.valortotal = calcularValorTotal();
    }

    public ServicioInsumo() {
    }

    public ServicioInsumo(ServicioInsumoPK id, int cantidadinsumo, Servicio servicio, Insumo insumo) {
        this.id = id;
        this.cantidadinsumo = cantidadinsumo;
        this.servicio = servicio;
        this.insumo = insumo;
        this.valortotal = calcularValorTotal();
    }

    private Double calcularValorTotal() {
        Double valorunitario = (insumo != null) ? insumo.getValorunitario() : 0.0;
        Double valorservicio = (servicio != null) ? servicio.getValorservicio() : 0.0;
        return (cantidadinsumo > 0 ? cantidadinsumo * valorunitario : 0) + valorservicio;
    }

    public ServicioInsumoPK getId() {
        return this.id;
    }

    public void setId(ServicioInsumoPK id) {
        this.id = id;
    }

    public int getCantidadinsumo() {
        return this.cantidadinsumo;
    }

    public void setCantidadinsumo(int cantidadinsumo) {
        this.cantidadinsumo = cantidadinsumo;
        this.valortotal = calcularValorTotal();
    }

    public Double getValortotal() {
        return this.valortotal;
    }

    public void setValortotal(Double valortotal) {
        this.valortotal = valortotal;
    }

    public Servicio getServicio() {
        return this.servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
        this.valortotal = calcularValorTotal();
    }

    public Insumo getInsumo() {
        return this.insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
        this.valortotal = calcularValorTotal();
    }
}
