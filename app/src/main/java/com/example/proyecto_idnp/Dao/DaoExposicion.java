package com.example.proyecto_idnp.Dao;
import com.example.proyecto_idnp.Entidades.Exposicion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoExposicion {

    @Insert
    void insert(Exposicion exposicion);

    @Update
    void update(Exposicion exposicion);

    @Delete
    void delete(Exposicion exposicion);

    @Query("SELECT * FROM Exposicion WHERE id = :id")
    Exposicion getExposicionById(int id);

    @Query("SELECT * FROM Exposicion")
    List<Exposicion> getAllExposiciones();

    @Query("DELETE FROM Exposicion")
    void deleteAll();

}

