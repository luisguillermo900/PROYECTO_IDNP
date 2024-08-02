package com.example.proyecto_idnp.Entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Autor {
    @PrimaryKey
    @NonNull
    public int id;
    @ColumnInfo(name = "url_imagen")
    public String urlImagen;
    @ColumnInfo(name = "nombre")
    public String nombre;

    @NonNull
    public int getIdAuthor() {
        return id;
    }

    public void setIdAuthor(@NonNull int idAuthor) {
        this.id = idAuthor;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public Autor(@NonNull int idAuthor, String urlImagen, String nombre) {
        this.id = idAuthor;
        this.urlImagen = urlImagen;
        this.nombre = nombre;
    }

    public Autor() {
    }

}
