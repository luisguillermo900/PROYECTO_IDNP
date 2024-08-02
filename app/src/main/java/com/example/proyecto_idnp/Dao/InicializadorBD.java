package com.example.proyecto_idnp.Dao;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import com.example.proyecto_idnp.Abstract.AppDatabase;
import com.example.proyecto_idnp.Entidades.Autor;
import com.example.proyecto_idnp.Entidades.Exposicion;
import com.example.proyecto_idnp.Entidades.Galeria;
import com.example.proyecto_idnp.Entidades.ObraDeArte;
import com.example.proyecto_idnp.Entidades.ResultadoFiltro;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InicializadorBD extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initializeDatabase();
    }

    private void initializeDatabase() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            DaoObra daoObra = db.daoObra();
            DaoAutor daoAutor = db.daoAuthor();
            DaoExposicion daoExposicion = db.daoExposicion();
            DaoGaleria daoGaleria = db.daoGaleria();
            Log.d(TAG, "initializeDatabase()");

            //INSERTANDO AUTORES
            if (daoAutor.getAllAutores().isEmpty()) {
                daoAutor.insertarAutor(new Autor(
                        1,
                        "https://bptfotografia.com/wp-content/uploads/2021/08/foto-de-retrato-tipos.jpg",
                        "Juan Perez"
                ));
                daoAutor.insertarAutor(new Autor(
                        2,
                        "https://previews.123rf.com/images/rawpixel/rawpixel1704/rawpixel170441704/76561515-retrato-de-personas-estudio-disparar-con-expresi%C3%B3n-de-cara-sonriente.jpg",
                        "Maria Juana"
                ));
                daoAutor.insertarAutor(new Autor(
                        3,
                        "https://img.freepik.com/foto-gratis/joven-barbudo-camisa_273609-5938.jpg?w=740&t=st=1721539590~exp=1721540190~hmac=f97868024806588bab30929224ef3a5ba87568eda000c14cb54bf6ec56335a20",
                        "Jose Castro"
                ));
                daoAutor.insertarAutor(new Autor(
                        3,
                        "https://pixnio.com/free-images/2017/11/30/2017-11-30-18-37-25-576x864.jpg",
                        "Lucia Flores"
                ));
            }

            //INSERTAR EXPOSICIONES
            if (daoExposicion.getAllExposiciones().isEmpty()) {
                daoExposicion.insert(new Exposicion(
                        1,
                        "https://scontent.faqp1-1.fna.fbcdn.net/v/t39.30808-6/447519333_894661172658611_6308906618339348145_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeE46AM9Amk18VAXc66N20CzQ6SwSFdQdTNDpLBIV1B1M9wMaAoQNgIGVYc8Rg_Ip3Vef8EQKhLp_aq8WDjMNmbX&_nc_ohc=RUFRL5SvpyoQ7kNvgHH7BFU&_nc_ht=scontent.faqp1-1.fna&oh=00_AYDvcs2Z3vUpeK1590cSVPUv_mAjEes3eSPCPia9nGKyHg&oe=66B0E723",
                        "La otra ciudad",
                        "11-06-2024"
                ));
                daoExposicion.insert(new Exposicion(
                        2,
                        "https://scontent.faqp3-1.fna.fbcdn.net/v/t39.30808-6/442435875_888610933263635_6870633362810967070_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeEa4YOx_CX-V2ynwj91Kd7ZHc8etd2s8xcdzx613azzF7huSFb6YpzW49X8GLy5b1Ho0lkjh-hFpxRiuB1-MVJ9&_nc_ohc=2PJ6xiv-b2sQ7kNvgHlvNe0&_nc_ht=scontent.faqp3-1.fna&oh=00_AYDSUxuFQqLLMmf5sk3-wkEKjysjWuGlZIs1AUMWIlH8KQ&oe=66B0F81C",
                        "Silencios revelados",
                        "11-06-2024"
                ));
                daoExposicion.insert(new Exposicion(
                        3,
                        "https://scontent.faqp3-1.fna.fbcdn.net/v/t39.30808-6/447677894_894660599325335_486979158550427425_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeH9LXqpxu4VVqx-zLjooB35UKyGONi0DRFQrIY42LQNESb3NeFdgKPDnw3frfyf5jEIEa2efR21I_gt-gZFZnxH&_nc_ohc=AhffGP2aWpgQ7kNvgHrSMhh&_nc_ht=scontent.faqp3-1.fna&gid=ARTY0714EFk0xpDsC8QRk0F&oh=00_AYBDVAL4pRDZnSHsCTLZH2Xl2SXfNTtJwX6hELx7nsJobg&oe=66B0EB5B",
                        "Expo FAU",
                        "05-06-2024"
                ));
            }

            //INSERTAR GALERIAS
            if (daoGaleria.getAllGalerias().isEmpty()) {
                daoGaleria.insertarGaleria(new Galeria(
                        1,
                        "Galeria 1",
                        1
                ));
                daoGaleria.insertarGaleria(new Galeria(
                        2,
                        "Galeria 2",
                        1
                ));
                daoGaleria.insertarGaleria(new Galeria(
                        3,
                        "Galeria 3",
                        2
                ));
                daoGaleria.insertarGaleria(new Galeria(
                        4,
                        "Galeria 4",
                        2
                ));
                daoGaleria.insertarGaleria(new Galeria(
                        5,
                        "Galeria 5",
                        3
                ));
                daoGaleria.insertarGaleria(new Galeria(
                        6,
                        "Galeria 6",
                        3
                ));
                daoGaleria.insertarGaleria(new Galeria(
                        7,
                        "Galeria 7",
                        3
                ));
            }

            //INSERTAR OBRAS
            if (daoObra.obtenerObras().isEmpty()) {
                Log.d(TAG, "Cargando datos de obras");
                daoObra.insertarObra(new ObraDeArte(
                        1,
                        "https://scontent.faqp1-1.fna.fbcdn.net/v/t39.30808-6/447519333_894661172658611_6308906618339348145_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeE46AM9Amk18VAXc66N20CzQ6SwSFdQdTNDpLBIV1B1M9wMaAoQNgIGVYc8Rg_Ip3Vef8EQKhLp_aq8WDjMNmbX&_nc_ohc=RUFRL5SvpyoQ7kNvgHH7BFU&_nc_ht=scontent.faqp1-1.fna&oh=00_AYDvcs2Z3vUpeK1590cSVPUv_mAjEes3eSPCPia9nGKyHg&oe=66B0E723",
                        "LA YAKANA EN ANTAKARI",
                        1,
                        1,
                        1,
                        "2024-05-12",
                        "Fotografia",
                        "Pintura de luz, larga exposición, apilado",
                        "DIMENSIONES: 50 x 30 cm.\n"
                ));
                daoObra.insertarObra(new ObraDeArte(
                        2,
                        "https://scontent.faqp3-1.fna.fbcdn.net/v/t39.30808-6/447677894_894660599325335_486979158550427425_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeH9LXqpxu4VVqx-zLjooB35UKyGONi0DRFQrIY42LQNESb3NeFdgKPDnw3frfyf5jEIEa2efR21I_gt-gZFZnxH&_nc_ohc=AhffGP2aWpgQ7kNvgHrSMhh&_nc_ht=scontent.faqp3-1.fna&gid=ARTY0714EFk0xpDsC8QRk0F&oh=00_AYBDVAL4pRDZnSHsCTLZH2Xl2SXfNTtJwX6hELx7nsJobg&oe=66B0EB5B",
                        "SOGAY",
                        1,
                        1,
                        1,
                        "2024-05-12",
                        "Fotografia",
                        "Larga exposiciòn",
                        "DIMENSIONES:  30 x 45 cm.\n"
                ));
                daoObra.insertarObra(new ObraDeArte(
                        3,
                        "https://scontent.faqp3-1.fna.fbcdn.net/v/t39.30808-6/442435875_888610933263635_6870633362810967070_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeEa4YOx_CX-V2ynwj91Kd7ZHc8etd2s8xcdzx613azzF7huSFb6YpzW49X8GLy5b1Ho0lkjh-hFpxRiuB1-MVJ9&_nc_ohc=2PJ6xiv-b2sQ7kNvgHlvNe0&_nc_ht=scontent.faqp3-1.fna&oh=00_AYDSUxuFQqLLMmf5sk3-wkEKjysjWuGlZIs1AUMWIlH8KQ&oe=66B0F81C",
                        "POCSI",
                        1,
                        1,
                        1,
                        "2024-05-14",
                        "Fotografia",
                        "Pintura de luz, y larga exposición",
                        "DIMENSIONES: 30 x 45 cm.\n"
                ));
            }





        });
    }
}
