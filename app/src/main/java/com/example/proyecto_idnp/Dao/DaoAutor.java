package com.example.proyecto_idnp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.proyecto_idnp.Entidades.Autor;
import com.example.proyecto_idnp.Entidades.ResultadoFiltro;

import java.util.List;

@Dao
public interface DaoAutor {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarAutor(Autor... autor);

    @Query("UPDATE Autor SET url_imagen = :url, nombre = :nombre WHERE id = :id")
    void actualizarAutor(int id, String url, String nombre);

    @Query("DELETE FROM Autor WHERE id = :id")
    void eliminarAutor(int id);

    @Query("SELECT * FROM Autor")
    List<Autor> getAllAutores();

    @Query("SELECT * FROM Autor WHERE id = :id")
    Autor getAutor(int id);

    @Query("SELECT nombre AS nombre, url_imagen AS urlImagen FROM Autor")
    List<ResultadoFiltro> filtrarAutores();

}
