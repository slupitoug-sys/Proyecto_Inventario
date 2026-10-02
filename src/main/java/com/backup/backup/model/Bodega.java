package com.backup.backup.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "bodega")
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBodega;

    private String nombre;

    private String descripcion;

    private String ubicacion;

    private Boolean estado;

    @OneToMany(mappedBy = "bodega")
    private List<Existencia> existencias;

    @OneToMany(mappedBy = "bodega")
    private List<EntradaInventario> entradasInventario;

    @OneToMany(mappedBy = "bodega")
    private List<Solicitud> solicitudes;

    @OneToMany(mappedBy = "bodega")
    private List<Entrega> entregas;

    public Bodega() {
    }

    public Bodega(Long idBodega, String nombre, String descripcion,
                  String ubicacion, Boolean estado) {

        this.idBodega = idBodega;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public Long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(Long idBodega) {
        this.idBodega = idBodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Existencia> getExistencias() {
        return existencias;
    }

    public void setExistencias(List<Existencia> existencias) {
        this.existencias = existencias;
    }

    public List<EntradaInventario> getEntradasInventario() {
        return entradasInventario;
    }

    public void setEntradasInventario(List<EntradaInventario> entradasInventario) {
        this.entradasInventario = entradasInventario;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<Entrega> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }
}