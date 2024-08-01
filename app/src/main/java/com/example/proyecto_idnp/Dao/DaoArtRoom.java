package com.example.proyecto_idnp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.proyecto_idnp.Entidades.ArtRoom;

import java.util.List;

@Dao
public interface DaoArtRoom {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArtRoom(ArtRoom artRoom);

    @Query("UPDATE artroom SET name = :name, description = :description WHERE id = :id")
    void updateArtRoom(String id, String name, String description);

    @Query("DELETE FROM artroom WHERE id = :id")
    void deleteArtRoom(String id);

    @Query("SELECT * FROM artroom")
    List<ArtRoom> getAllArtRoom();

    @Query("SELECT * FROM artroom WHERE id = :id")
    ArtRoom getArtRoom(String id);
}
