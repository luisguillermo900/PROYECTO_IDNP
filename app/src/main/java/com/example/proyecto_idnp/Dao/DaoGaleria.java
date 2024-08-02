package com.example.proyecto_idnp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.proyecto_idnp.Entidades.Galeria;

import java.util.List;

@Dao
public interface DaoGaleria {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarGaleria(Galeria galeria);

    @Query("UPDATE Galeria SET nombre = :name WHERE id = :id")
    void actualizarGaleria(int id, String name);

    @Query("DELETE FROM Galeria WHERE id = :id")
    void eliminarGaleria(int id);

    @Query("SELECT * FROM Galeria")
    List<Galeria> getAllGalerias();

    @Query("SELECT * FROM Galeria WHERE id = :id")
    Galeria getGaleria(int id);
}
