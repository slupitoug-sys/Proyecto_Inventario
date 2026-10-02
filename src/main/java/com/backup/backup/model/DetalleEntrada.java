package com.backup.backup.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_entrada")
public class DetalleEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleEntrada;

    private Integer cantidad;

    private Double costoUnitario;

    @ManyToOne
    @JoinColumn(name = "id_entrada")
    private EntradaInventario entradaInventario;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public DetalleEntrada() {
    }

    public DetalleEntrada(Long idDetalleEntrada, Integer cantidad,
                          Double costoUnitario,
                          EntradaInventario entradaInventario,
                          Producto producto) {
        this.idDetalleEntrada = idDetalleEntrada;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
        this.entradaInventario = entradaInventario;
        this.producto = producto;
    }

    public Long getIdDetalleEntrada() {
        return idDetalleEntrada;
    }

    public void setIdDetalleEntrada(Long idDetalleEntrada) {
        this.idDetalleEntrada = idDetalleEntrada;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public EntradaInventario getEntradaInventario() {
        return entradaInventario;
    }

    public void setEntradaInventario(EntradaInventario entradaInventario) {
        this.entradaInventario = entradaInventario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}