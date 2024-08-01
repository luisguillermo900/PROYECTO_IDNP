package com.example.proyecto_idnp.Entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(entity = Author.class,
                        parentColumns = "id",
                        childColumns = "id_autor",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Exposicion.class,
                        parentColumns = "id",
                        childColumns = "id_exposicion",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = ArtRoom.class,
                        parentColumns = "id",
                        childColumns = "id_galeria",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class ObraDeArte {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "url_imagen")
    private String urlImagen;

    @ColumnInfo(name = "titulo")
    private String titulo;
    //-------------------Llaves foranesas----
    @ColumnInfo(name = "id_autor")
    private int idAutor;

    @ColumnInfo(name = "id_exposicion")
    private int idExposicion;

    @ColumnInfo(name = "id_galeria")
    private int idGaleria;
    //--------------------------------
    @ColumnInfo(name = "fecha")
    private String fecha;

    @ColumnInfo(name = "tipo")
    private String tipo;

    @ColumnInfo(name = "tecnica")
    private String tecnica;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    public ObraDeArte(int id, String urlImagen, String titulo, int idAutor, int idExposicion, int idGaleria, String fecha, String tipo, String tecnica, String descripcion) {
        this.id = id;
        this.urlImagen = urlImagen;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.idExposicion = idExposicion;
        this.idGaleria = idGaleria;
        this.fecha = fecha;
        this.tipo = tipo;
        this.tecnica = tecnica;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
    }

    public int getIdGaleria() {
        return idGaleria;
    }

    public void setIdGaleria(int idGaleria) {
        this.idGaleria = idGaleria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
