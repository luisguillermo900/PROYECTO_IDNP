package com.example.proyecto_idnp.Abstract;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyecto_idnp.Dao.DaoArtRoom;
import com.example.proyecto_idnp.Dao.DaoAuthor;
import com.example.proyecto_idnp.Dao.DaoPicture;
import com.example.proyecto_idnp.Dao.DaoPictureAuthor;
import com.example.proyecto_idnp.Dao.DaoUsuario;
import com.example.proyecto_idnp.Entidades.ArtRoom;
import com.example.proyecto_idnp.Entidades.Author;
import com.example.proyecto_idnp.Entidades.AuthorWithPictures;
import com.example.proyecto_idnp.Entidades.Picture;
import com.example.proyecto_idnp.Entidades.Usuario;

@Database(
        entities = {Usuario.class, Author.class, Picture.class, ArtRoom.class},
        version  = 9
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

    //faltan agregar
    //public abstract DaoUsuario daoUsuario();
    public abstract DaoArtRoom daoArtRoom();
    //public abstract DaoPicture daoPicture();
    public abstract DaoAuthor daoAuthor();
    //public abstract DaoPictureAuthor daoPictureAuthor();
}
