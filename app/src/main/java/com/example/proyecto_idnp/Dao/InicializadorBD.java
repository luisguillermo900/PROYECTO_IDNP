package com.example.proyecto_idnp.Dao;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import com.example.proyecto_idnp.Abstract.AppDatabase;
import com.example.proyecto_idnp.Entidades.ObraDeArte;

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
            Log.d(TAG, "initializeDatabase()");

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
            } else {
                Log.d(TAG, daoObra.obtenerObras().toString());
            }
        });
    }
}
