package com.backup.backup.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ajuste_inventario")
public class AjusteInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAjuste;

    private Integer cantidadAnterior;

    private Integer cantidadNueva;

    private String motivo;

    private LocalDateTime fechaAjuste;

    @ManyToOne
    @JoinColumn(name = "id_existencia")
    private Existencia existencia;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public AjusteInventario() {
    }

    public AjusteInventario(Long idAjuste, Integer cantidadAnterior,
                            Integer cantidadNueva, String motivo,
                            LocalDateTime fechaAjuste,
                            Existencia existencia, Usuario usuario) {

        this.idAjuste = idAjuste;
        this.cantidadAnterior = cantidadAnterior;
        this.cantidadNueva = cantidadNueva;
        this.motivo = motivo;
        this.fechaAjuste = fechaAjuste;
        this.existencia = existencia;
        this.usuario = usuario;
    }

    public Long getIdAjuste() {
        return idAjuste;
    }

    public void setIdAjuste(Long idAjuste) {
        this.idAjuste = idAjuste;
    }

    public Integer getCantidadAnterior() {
        return cantidadAnterior;
    }

    public void setCantidadAnterior(Integer cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    public Integer getCantidadNueva() {
        return cantidadNueva;
    }

    public void setCantidadNueva(Integer cantidadNueva) {
        this.cantidadNueva = cantidadNueva;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFechaAjuste() {
        return fechaAjuste;
    }

    public void setFechaAjuste(LocalDateTime fechaAjuste) {
        this.fechaAjuste = fechaAjuste;
    }

    public Existencia getExistencia() {
        return existencia;
    }

    public void setExistencia(Existencia existencia) {
        this.existencia = existencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}