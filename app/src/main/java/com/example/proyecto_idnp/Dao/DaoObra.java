package com.example.proyecto_idnp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.proyecto_idnp.Entidades.ObraDeArte;

import java.util.List;

@Dao
public interface DaoObra {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarObra(ObraDeArte... obras);

    @Query("UPDATE ObraDeArte SET  url_imagen= :urlImagen, titulo = :titulo, id_autor = :idAutor, id_exposicion = :idExposicion, id_galeria = :idGaleria, fecha = :fecha, tipo = :tipo, tecnica = :tecnica, descripcion = :descripcion WHERE id = :id")
    void actualizarObra(int id, String urlImagen, String titulo, int idAutor, int idExposicion, int idGaleria, String fecha, String tipo, String tecnica, String descripcion);

    @Query("DELETE FROM ObraDeArte WHERE id = :id")
    void eliminarPictura(int id);

    @Query("SELECT * FROM ObraDeArte")
    List<ObraDeArte> obtenerObras();

    @Query("SELECT * FROM ObraDeArte WHERE id = :id")
    ObraDeArte obtenerObra(int id);
}
