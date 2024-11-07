package com.serviceback.serviceback.domain.entities;

import java.util.Date;

import java.util.List;

import com.serviceback.serviceback.domain.entities.security.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @Column(length = 10, nullable = false)
    private String nrodoc;

    @Column(length = 40)
    private String nombre;
    @Column(length = 40)
    private String apellido;
    @Column
    private Date fecharegistro;

    @ManyToOne
    private Sucursal sucursal;
    @ManyToOne
    private TipoPersona tipoPersona;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
    private List<OrdenServicio> ordenesComoCliente;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.REMOVE)
    private List<OrdenServicio> ordenesComoEmpleado;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.REMOVE)
    private List<Transaccion> transacciones;

    @PrePersist
    protected void onCreate() {
        fecharegistro = new Date();
    }

    public Persona() {
    }

    public Persona(String nrodoc, String nombre, String apellido, Sucursal sucursal,
            TipoPersona tipoPersona, User user) {
        this.nrodoc = nrodoc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sucursal = sucursal;
        this.tipoPersona = tipoPersona;
        this.user = user;
    }

    public String getNrodoc() {
        return this.nrodoc;
    }

    public void setNrodoc(String nrodoc) {
        this.nrodoc = nrodoc;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecharegistro() {
        return this.fecharegistro;
    }

    public Sucursal getSucursal() {
        return this.sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public TipoPersona getTipoPersona() {
        return this.tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
