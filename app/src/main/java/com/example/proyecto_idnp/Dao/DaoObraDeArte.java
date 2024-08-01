package com.example.proyecto_idnp.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyecto_idnp.Entidades.ObraDeArte;

import java.util.List;
@Dao
public interface DaoObraDeArte {

    @Query("UPDATE ObraDeArte SET url_imagen = :urlImagen, titulo = :titulo, id_autor = :idAutor, id_exposicion = :idExposicion, id_galeria = :idGaleria, fecha = :fecha, tipo = :tipo, tecnica = :tecnica, descripcion = :descripcion WHERE id = :id")
    void updateObraDeArte(int id, String urlImagen, String titulo, int idAutor, int idExposicion, int idGaleria, String fecha, String tipo, String tecnica, String descripcion);

    @Insert
    void insert(ObraDeArte obraDeArte);

    @Update
    void update(ObraDeArte obraDeArte);

    @Delete
    void delete(ObraDeArte obraDeArte);

    @Query("SELECT * FROM ObraDeArte")
    List<ObraDeArte> getAllObrasDeArte();

    @Query("SELECT * FROM ObraDeArte WHERE id = :id")
    ObraDeArte getPicture(String id);

}
