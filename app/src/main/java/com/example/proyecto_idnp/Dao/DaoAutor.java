package com.example.proyecto_idnp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.proyecto_idnp.Entidades.Autor;

import java.util.List;

@Dao
public interface DaoAutor {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarAutor(Autor... autor);

    @Query("UPDATE Autor SET nombres = :name, apellidos = :surname WHERE id = :id")
    void actualizarAutor(int id, String name, String surname);

    @Query("DELETE FROM Autor WHERE id = :id")
    void eliminarAutor(int id);

    @Query("SELECT * FROM Autor")
    List<Autor> getAllAutores();

    @Query("SELECT * FROM Autor WHERE id = :id")
    Autor getAutor(int id);

}
