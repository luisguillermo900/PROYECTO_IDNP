package com.example.proyecto_idnp.Entidades;

public class ResultadoFiltro {
    private String urlImagen;
    private String nombre;

    public ResultadoFiltro(String urlImagen, String nombre) {
        this.urlImagen = urlImagen;
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}