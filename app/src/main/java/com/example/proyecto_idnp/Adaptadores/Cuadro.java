package com.example.proyecto_idnp.Adaptadores;

public class Cuadro {
    private int id;
    private String nombreCuadro;
    private String descripcionCuadro;
    private String fotoCuadro;
    private String exposicionCuadro;
    public Cuadro(int id, String nombre, String descripcion, String foto, String exposicion) {
        this.id = id;
        this.nombreCuadro = nombre;
        this.descripcionCuadro = descripcion;
        this.fotoCuadro = foto;
        this.exposicionCuadro = exposicion;
    }
    public String getExposicionCuadro() {
        return exposicionCuadro;
    }

    public void setExposicionCuadro(String exposicionCuadro) {
        this.exposicionCuadro = exposicionCuadro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCuadro() {
        return nombreCuadro;
    }

    public void setNombreCuadro(String nombre) {
        this.nombreCuadro = nombre;
    }

    public String getDescripcionCuadro() {
        return descripcionCuadro;
    }

    public void setDescripcionCuadro(String descripcion) {
        this.descripcionCuadro = descripcion;
    }

    public String getFotoCuadro() {
        return fotoCuadro;
    }

    public void setFotoCuadro(String foto) {
        this.fotoCuadro = foto;
    }
}
