package com.example.proyecto_idnp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.proyecto_idnp.Entidades.ObraDeArte;
import com.example.proyecto_idnp.Entidades.ResultadoFiltro;

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

    @Query("SELECT tipo AS nombre, url_imagen AS urlImagen FROM ObraDeArte GROUP BY tipo")
    List<ResultadoFiltro> filtrarTipos();

    @Query("SELECT * FROM ObraDeArte WHERE tipo = :tipo")
    List<ObraDeArte> cargarObrasPorTipo(String tipo);

    @Query("SELECT * FROM ObraDeArte " +
            "    INNER JOIN Autor ON ObraDeArte.id_autor = Autor.id " +
            "    WHERE Autor.nombre = :autor")
    List<ObraDeArte> cargarObrasPorAutor(String autor);

    @Query("SELECT * FROM ObraDeArte " +
            "    INNER JOIN Galeria ON ObraDeArte.id_galeria = Galeria.id " +
            "    WHERE Galeria.nombre = :galeria")
    List<ObraDeArte> cargarObrasPorGaleria(String galeria);

    @Query("SELECT * FROM ObraDeArte INNER JOIN Exposicion ON ObraDeArte.id_exposicion = Exposicion.id WHERE Exposicion.nombre = :exposicion")
    List<ObraDeArte> cargarObrasPorExposicion(String exposicion);
}
