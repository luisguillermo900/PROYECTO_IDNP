package com.example.proyecto_idnp.Abstract;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyecto_idnp.Dao.DaoAutor;
import com.example.proyecto_idnp.Dao.DaoExposicion;
import com.example.proyecto_idnp.Dao.DaoGaleria;
import com.example.proyecto_idnp.Dao.DaoObra;
import com.example.proyecto_idnp.Entidades.Galeria;
import com.example.proyecto_idnp.Entidades.Autor;
import com.example.proyecto_idnp.Entidades.Exposicion;
import com.example.proyecto_idnp.Entidades.ObraDeArte;

@Database(
        entities = {
                Autor.class,
                ObraDeArte.class,
                Galeria.class,
                Exposicion.class},
        version  = 16
)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;
    private static String dataBaseName = "BaseDeDatosProyecto";
    public synchronized static AppDatabase getInstance(Context context){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, dataBaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
    //faltan agregar:
    //public abstract DaoUsuario daoUsuario();
    public abstract DaoGaleria daoGaleria();
    //public abstract DaoPicture daoPicture();
    public abstract DaoAutor daoAuthor();
    public abstract DaoObra daoObra();
    public abstract DaoExposicion daoExposicion();
    //public abstract DaoPictureAuthor daoPictureAuthor();
}
