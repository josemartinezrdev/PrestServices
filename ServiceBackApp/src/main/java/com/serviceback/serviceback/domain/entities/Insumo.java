package com.serviceback.serviceback.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "insumos")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 10, unique = true)
    private String codinterno;
    @Column(length = 40)
    private String nombre;
    @Column(length = 10)
    private double valorunitario;
    @Column
    private int stock;
    @Column
    private int stockmin;
    @Column
    private int stockmax;

    public Insumo() {
    }

    public Insumo(int id, String codinterno, String nombre, double valorunitario, int stock, int stockmin,
            int stockmax) {
        this.id = id;
        this.codinterno = codinterno;
        this.nombre = nombre;
        this.valorunitario = valorunitario;
        this.stock = stock;
        this.stockmin = stockmin;
        this.stockmax = stockmax;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodinterno() {
        return this.codinterno;
    }

    public void setCodinterno(String codinterno) {
        this.codinterno = codinterno;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValorunitario() {
        return this.valorunitario;
    }

    public void setValorunitario(double valorunitario) {
        this.valorunitario = valorunitario;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockmin() {
        return this.stockmin;
    }

    public void setStockmin(int stockmin) {
        this.stockmin = stockmin;
    }

    public int getStockmax() {
        return this.stockmax;
    }

    public void setStockmax(int stockmax) {
        this.stockmax = stockmax;
    }

}
