package com.backup.backup.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_entrega")
public class DetalleEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleEntrega;

    private Integer cantidadEntregada;

    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_entrega")
    private Entrega entrega;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public DetalleEntrega() {
    }

    public DetalleEntrega(Long idDetalleEntrega, Integer cantidadEntregada,
                          String observaciones, Entrega entrega,
                          Producto producto) {
        this.idDetalleEntrega = idDetalleEntrega;
        this.cantidadEntregada = cantidadEntregada;
        this.observaciones = observaciones;
        this.entrega = entrega;
        this.producto = producto;
    }

    public Long getIdDetalleEntrega() {
        return idDetalleEntrega;
    }

    public void setIdDetalleEntrega(Long idDetalleEntrega) {
        this.idDetalleEntrega = idDetalleEntrega;
    }

    public Integer getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(Integer cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}