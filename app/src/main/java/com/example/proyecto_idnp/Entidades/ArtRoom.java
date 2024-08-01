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
    public String idRoom;
    public String name;
    public String description;

    @ColumnInfo(name = "id_exposicion")
    public int idExposicion;

    public ArtRoom(){}

    //Constructor con id Id Foraneo
    public ArtRoom(int idExposicion, String description, String name, @NonNull String idRoom) {
        this.idExposicion = idExposicion;
        this.description = description;
        this.name = name;
        this.idRoom = idRoom;
    }

    //Constructor sin Id Foraneo
    public ArtRoom(@NonNull String idRoom, String name, String description) {
        this.idRoom = idRoom;
        this.name = name;
        this.description = description;
    }

    @NonNull
    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(@NonNull String idRoom) {
        this.idRoom = idRoom;
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
