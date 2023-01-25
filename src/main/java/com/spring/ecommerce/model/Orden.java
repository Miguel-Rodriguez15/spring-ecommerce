package com.spring.ecommerce.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    private Date FechaCreacion;
    private Date FechaRecibida;
    private double total;
    //Relacion de muchos auno
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalle;

    public Orden() {
        // TODO Auto-generated constructor stub
    }

    public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, double total) {
        super();
        this.id = id;
        this.numero = numero;
        FechaCreacion = fechaCreacion;
        FechaRecibida = fechaRecibida;
        this.total = total;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public Date getFechaRecibida() {
        return FechaRecibida;
    }

    public void setFechaRecibida(Date fechaRecibida) {
        FechaRecibida = fechaRecibida;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<DetalleOrden> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleOrden> detalle) {
        this.detalle = detalle;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Orden [id=" + id + ", numero=" + numero + ", FechaCreacion=" + FechaCreacion + ", FechaRecibida="
                + FechaRecibida + ", total=" + total + "]";
    }

}
