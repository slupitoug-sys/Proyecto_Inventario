package com.backup.backup.model;

import jakarta.persistence.*;

@Entity
@Table(name = "existencia")
public class Existencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExistencia;

    private Integer cantidadDisponible;

    private Integer existenciaMinima;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_bodega")
    private Bodega bodega;

    public Existencia() {
    }

    public Existencia(Long idExistencia, Integer cantidadDisponible,
                      Integer existenciaMinima, Producto producto,
                      Bodega bodega) {
        this.idExistencia = idExistencia;
        this.cantidadDisponible = cantidadDisponible;
        this.existenciaMinima = existenciaMinima;
        this.producto = producto;
        this.bodega = bodega;
    }

    public Long getIdExistencia() {
        return idExistencia;
    }

    public void setIdExistencia(Long idExistencia) {
        this.idExistencia = idExistencia;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Integer getExistenciaMinima() {
        return existenciaMinima;
    }

    public void setExistenciaMinima(Integer existenciaMinima) {
        this.existenciaMinima = existenciaMinima;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }
}