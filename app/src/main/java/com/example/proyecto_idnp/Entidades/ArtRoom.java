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
public class ArtRoom {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    public String name;
    public String description;

    @ColumnInfo(name = "id_exposicion")
    public int idExposicion;

    public ArtRoom(){}

    //Constructor con id Id Foraneo
    public ArtRoom(int idExposicion, String description, String name, @NonNull int idRoom) {
        this.idExposicion = idExposicion;
        this.description = description;
        this.name = name;
        this.id = idRoom;
    }
    //Constructor sin Id Foraneo
    public ArtRoom(@NonNull int idRoom, String name, String description) {
        this.id = idRoom;
        this.name = name;
        this.description = description;
    }

    @NonNull
    public int getIdRoom() {
        return id;
    }

    public void setIdRoom(@NonNull int idRoom) {
        this.id = idRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdExposicion() {return idExposicion;}

    public void setIdExposicion(int idExposicion) {this.idExposicion = idExposicion; }
}
