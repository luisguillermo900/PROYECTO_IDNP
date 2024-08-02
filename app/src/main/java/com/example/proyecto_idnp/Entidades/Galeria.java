package com.example.proyecto_idnp.Entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = @ForeignKey(entity = Exposicion.class,
                parentColumns = "id",
                childColumns = "id_exposicion",
                onDelete = ForeignKey.CASCADE)
)
public class Galeria {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "id_exposicion")
    public int idExposicion;

    public Galeria(){}

    //Constructor con id Id Foraneo
    public Galeria(@NonNull int id, String name, int idExposicion) {
        this.idExposicion = idExposicion;
        this.nombre = name;
        this.id = id;
    }
    //Constructor sin Id Foraneo
    public Galeria(@NonNull int id, String name) {
        this.id = id;
        this.nombre = name;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int idRoom) {
        this.id = idRoom;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public int getIdExposicion() {return idExposicion;}

    public void setIdExposicion(int idExposicion) {this.idExposicion = idExposicion; }
}
