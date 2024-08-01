package com.example.proyecto_idnp.Entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Author {
    @PrimaryKey
    @NonNull
    public String idAuthor;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "surname")
    public String surname;

    @NonNull
    public String getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(@NonNull String idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Author(@NonNull String idAuthor, String name, String surname) {
        this.idAuthor = idAuthor;
        this.name = name;
        this.surname = surname;
    }

    public Author() {
    }
}
