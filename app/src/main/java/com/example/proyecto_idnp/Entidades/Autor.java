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
    @ColumnInfo(name = "nombres")
    public String nombres;
    @ColumnInfo(name = "apellidos")
    public String apellidos;

    @NonNull
    public int getIdAuthor() {
        return id;
    }

    public void setIdAuthor(@NonNull int idAuthor) {
        this.id = idAuthor;
    }

    public String getName() {
        return nombres;
    }

    public void setName(String name) {
        this.nombres = name;
    }

    public String getSurname() {
        return apellidos;
    }

    public void setSurname(String surname) {
        this.apellidos = surname;
    }

    public Autor(@NonNull int idAuthor, String name, String surname) {
        this.id = idAuthor;
        this.nombres = name;
        this.apellidos = surname;
    }

    public Autor() {
    }

}
