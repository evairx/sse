package com.edutech.cl.edutech.model;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

import java.util.Date;
@Data

public class CursoModel extends RepresentationModel<CursoModel> {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String estado;
    private Date fecha;

    public CursoModel() {}

    public CursoModel(Integer id, String nombre, String descripcion, String categoria, String estado, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.estado = estado;
        this.fecha = fecha;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
