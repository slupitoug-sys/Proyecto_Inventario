package com.backup.backup.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String codigo;

    private String nombre;

    private String descripcion;

    private String unidadMedida;

    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    private List<Existencia> existencias;

    @OneToMany(mappedBy = "producto")
    private List<DetalleEntrada> detallesEntrada;

    @OneToMany(mappedBy = "producto")
    private List<DetalleSolicitud> detallesSolicitud;

    @OneToMany(mappedBy = "producto")
    private List<DetalleEntrega> detallesEntrega;

    public Producto() {
    }

    public Producto(Long idProducto, String codigo, String nombre,
                    String descripcion, String unidadMedida,
                    Boolean estado, Categoria categoria) {

        this.idProducto = idProducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.estado = estado;
        this.categoria = categoria;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Existencia> getExistencias() {
        return existencias;
    }

    public void setExistencias(List<Existencia> existencias) {
        this.existencias = existencias;
    }

    public List<DetalleEntrada> getDetallesEntrada() {
        return detallesEntrada;
    }

    public void setDetallesEntrada(List<DetalleEntrada> detallesEntrada) {
        this.detallesEntrada = detallesEntrada;
    }

    public List<DetalleSolicitud> getDetallesSolicitud() {
        return detallesSolicitud;
    }

    public void setDetallesSolicitud(List<DetalleSolicitud> detallesSolicitud) {
        this.detallesSolicitud = detallesSolicitud;
    }

    public List<DetalleEntrega> getDetallesEntrega() {
        return detallesEntrega;
    }

    public void setDetallesEntrega(List<DetalleEntrega> detallesEntrega) {
        this.detallesEntrega = detallesEntrega;
    }
}