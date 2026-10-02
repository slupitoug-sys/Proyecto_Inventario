package com.backup.backup.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_solicitud")
public class DetalleSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleSolicitud;

    private Integer cantidadSolicitada;

    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public DetalleSolicitud() {
    }

    public DetalleSolicitud(Long idDetalleSolicitud, Integer cantidadSolicitada,
                            String observaciones, Solicitud solicitud,
                            Producto producto) {
        this.idDetalleSolicitud = idDetalleSolicitud;
        this.cantidadSolicitada = cantidadSolicitada;
        this.observaciones = observaciones;
        this.solicitud = solicitud;
        this.producto = producto;
    }

    public Long getIdDetalleSolicitud() {
        return idDetalleSolicitud;
    }

    public void setIdDetalleSolicitud(Long idDetalleSolicitud) {
        this.idDetalleSolicitud = idDetalleSolicitud;
    }

    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}