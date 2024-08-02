package com.example.proyecto_idnp.Dao;
import com.example.proyecto_idnp.Entidades.Exposicion;
import com.example.proyecto_idnp.Entidades.ResultadoFiltro;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoExposicion {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Exposicion exposicion);

    @Query("UPDATE Exposicion SET url_imagen = :urlImagen, nombre = :nom, fecha = :fecha WHERE id = :id")
    void update(int id, String urlImagen, String nom, String fecha);

    @Delete
    void delete(Exposicion exposicion);

    @Query("SELECT * FROM Exposicion WHERE id = :id")
    Exposicion getExposicionById(int id);

    @Query("SELECT * FROM Exposicion WHERE nombre = :nombre")
    Exposicion getExposicionPorNombre(String nombre);

    @Query("SELECT * FROM Exposicion")
    List<Exposicion> getAllExposiciones();

    @Query("DELETE FROM Exposicion")
    void deleteAll();

    @Query("SELECT nombre AS nombre, url_imagen AS urlImagen FROM Exposicion")
    List<ResultadoFiltro> filtrarExposiciones();
}

