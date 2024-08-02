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
                        4,
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
                        "audio_cuadro.mp3",
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
                        "audio_cuadro.mp3",
                        "SOGAY",
                        1,
                        1,
                        2,
                        "2024-05-12",
                        "Fotografia",
                        "Larga exposiciòn",
                        "DIMENSIONES:  30 x 45 cm.\n"
                ));
                daoObra.insertarObra(new ObraDeArte(
                        3,
                        "https://scontent.faqp3-1.fna.fbcdn.net/v/t39.30808-6/442435875_888610933263635_6870633362810967070_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeEa4YOx_CX-V2ynwj91Kd7ZHc8etd2s8xcdzx613azzF7huSFb6YpzW49X8GLy5b1Ho0lkjh-hFpxRiuB1-MVJ9&_nc_ohc=2PJ6xiv-b2sQ7kNvgHlvNe0&_nc_ht=scontent.faqp3-1.fna&oh=00_AYDSUxuFQqLLMmf5sk3-wkEKjysjWuGlZIs1AUMWIlH8KQ&oe=66B0F81C",
                        "audio_cuadro.mp3",
                        "POCSI",
                        1,
                        1,
                        2,
                        "2024-05-14",
                        "Fotografia",
                        "Pintura de luz, y larga exposición",
                        "DIMENSIONES: 30 x 45 cm.\n"
                ));
                daoObra.insertarObra(new ObraDeArte(
                        4,
                        "https://drive.google.com/u/1/drive-viewer/AKGpihaMhrsowZVOzKBzTYzNyI0gamCWWTim8a42SbZMza8ceKz7C8TgU1qcKLQzLN0ZcECcGHlROgPGd3q19OUkKQk9WDGrPwg5jQ=s1600-rw-v1",
                        "11.acc",
                        "San Lazaro - Ayacucho",
                        2,
                        2,
                        3,
                        "2024-05-12",
                        "Pintura",
                        "Pintura de luz, larga exposición, apilado",
                        "En esta pintura se captura una esquina pintoresca de una ciudad colonial, con sus edificios de fachadas coloridas y detalles arquitectónicos clásicos. El cielo despejado y azul resalta la claridad y la luminosidad del día. Los contrastes entre las sombras profundas y las paredes iluminadas crean una sensación de profundidad y realismo. La composición invita al espectador a pasear por estas calles tranquilas y a admirar la belleza atemporal de la arquitectura histórica."
                ));
                daoObra.insertarObra(new ObraDeArte(
                        5,
                        "https://lh3.google.com/u/0/d/1yqkJ5ONeikhKMFhTvbDNDw9Yb9WVHWVj=w1366-h643-iv1",
                        "10.acc",
                        "Madre y su hijo",
                        2,
                        2,
                        4,
                        "2024-05-12",
                        "Pintura",
                        "Pintura de luz, larga exposición, apilado",
                        "En esta pintura, se representa un momento íntimo y tierno entre una madre y su hijo. La madre sostiene al bebé con cariño, y ambos comparten una expresión de amor y tranquilidad. Los tonos suaves y la pincelada suelta crean una atmósfera de calidez y afecto. El fondo azul pastel resalta la conexión emocional entre los dos, haciendo que el espectador sienta la profundidad de este vínculo maternal."
                ));
                daoObra.insertarObra(new ObraDeArte(
                        6,
                        "https://lh3.googleusercontent.com/fife/ALs6j_H-6MHpMAMxmbeTxxZ2yKFsKZCopMAoZwZ0QLc7OG8TpDZaycn_bq6DK23wiNGH1M-SaA2b7SD1e9TurNiO9Y5RjT0xoAQ-fXZ7SyrAq-tEXAnzpzOZ5OLzY9wTjZBhmUvfYLsgsffAfn9r2WXLf-TErCSwgj8fwbCd7eYY7IkrJD6CWW6RK67YC7OyWys47_Q10wwoAOC2W2MwC8kvEpUhil6RKRWrTHiCGHWb3n66TtyxZvM44tzaJjgv2eq3bycyljQEBqprL_hVRPnhLEj1_VvSG3GUrooBYH4I8z_27W_P0rTJDKAjChXMhaK7hx6wt2L4-i5t8cIxSJUxRS8Mgt8sqhwaeOnehycqOZHGevT5BgijKlpwnJn03LrOxV39gDFrqyA5nmgnoe6-uMFnHDzoyM1rAj6oIq7a2pqIWaPQrEA-KsMe5zqHsslw1f8u8PD0lDEfaounn77u3BgPdkWy730qC5bHBnrlUcDDmnU3JURTRSb_A38HqEH8IXpal3vhI0fC8otU3uCS8PNIo_swymEIoicNUmxgB19ZRZNMJk-3hcBaC4xKC7Qhwf1HD9ePy5_TAfCvYWPY4ZxMJnXOC1rYfQmReZ7TQ9WnhZ2FvBN4JHGLIJnqqm52OZIKvHAW9AvEEJUsAbo7ZPA8Lhkv4oEsHU6GdNs2YethFA6IE5xH1Ob-wA7lLxMaTbCzysLTVa4lLnZUuNQYUSrN_9vfBxZi9c3RW26ATvoulpX_8z8O6O-PPG8sKvk9zMUupoaHDu64Xi_Vi2bxVMt-_MfcP31S0vtMV3zNBPYrnf8u3GoEBXlMowuTwi_jJW0ECi0bWZcVMlZy4-S-oVrjA79JzAh5AsrHPbM0lni04rnb_tboiuiy0avHgNEsIovbhucNLx8YYxYF_f-uGDVKNTMnbu1XUuBF3VsW7r9IOYigyAo_rFnWT2CRLDyollkyBr3Z-jnTR4BimgPVhnb_YMIDs6uX9HUmyhy7wnQieuQWYDg8NfW_3FN2MHNHWWtg-wBc2XnTfcNee5p3st_GzdNDJAieIChTxP5BN69uyVAdoSX89FS01E-YSa-y52OlX13NCFfzvS6KsYF8ac7qc3TV7zZ-giKN9Kwnfmou6BWMs_fYWR3lCtMZIfOr792sLzJdaZWev9QujtDQTdBuJfcXiymQPZ2wZ_OvMeDjlwHELsJMqdwkeL1cvaiIbBSgyCiwKRwbeX5LxOee8N8oxJ5ya8xjy1eUfxeCWqgNdKbarh2vNwFYI3i8XV889ukAvZa3DE374o29ll-vdRHNGwXyISOAQLuin1NXf5DxMyWvReAReSg_MpVJoNQJHeOKipTFTxc-qAV0qahaSWBFray6TlRYRRFRM-v3vzzabJMvDfZzNa2Vn7GzK8w71JPCalRclfNGQp8E4bKCKpBkZS6Sj8HTTDvmSSNYC7v5DcemepVlIfSEcUmKIZaoHOfLliRtce_zwHytPogGcyoRglSHBwNzPvcba9UfLnm0IyQa9ZfCiL7XeTC9PJPcNt-q-10nBa2Be9UoBubBnoidj77eN5TFjqvhNOqhIompuPNDv-ti-GdzFQMCZ2p25J4891pQyQ529q8X6_r3y3A36rZHbsd3R698YXuw2w4yvtJ1-11n1x2B_Kc=w1366-h643",
                        "2.acc",
                        "Habitual",
                        3,
                        3,
                        5,
                        "2024-05-12",
                        "Pintura",
                        "Pintura de luz, larga exposición, apilado",
                        "En esta pintura, se muestra a una llama antropomórfica recostada en una cama, envuelta en sábanas blancas y una manta naranja. Con una expresión relajada, la llama sostiene un celular, probablemente disfrutando de un momento de conexión y entretenimiento. La escena mezcla lo cotidiano con lo fantástico, utilizando una paleta de colores suaves y cálidos que evoca tranquilidad y confort en este momento íntimo de la vida moderna."
                ));
                daoObra.insertarObra(new ObraDeArte(
                        7,
                        "https://lh3.googleusercontent.com/fife/ALs6j_GbjgCDGnNA6c9F_F2U-Kf-kfFlH2st7H-xIz2TYZNM5_CpGa-g8nNHsxzH733umuQMCf4dig-F_9vbL6y6c0mQ8kAEDNs-bttT-PXX55vC8msQEflmQbobVx7EckF9_xNkFgDu1g2udCid24QRQvYNR99msanFD0IMjppZRzHtHXF0LgS8NcTxAIlNl2JtbIMgt3TqASDFjDK52SpSBZvI2zlav34MSgCANqWdRhe2A4Iru2CZh12AsfdjunCjoIxPP_Jlb4gKGxy4TpADu7VqVK7W15e8AjmXOY8R5jKGbet0EIMtVbmN5IFxdb1K97X6Zz6NWT2CtaI63chyW23tnEabbgVPjBbqcUJGazK_-4U_7cr5chcEU_1j6LE5HPHviTqnh_R3ELBLoPUfQcO6sZTQikzAg6-_pgRC1CyyDnvid0X-zmZtD-wqACw_aqdOFabVNQGHk7EUFAhMDqC3Bk9lT4QfCKkdCiJDmKE3jI_w-C0xQTjQ0IcFHN_S1P31KjsxzRWUjuANEiz9MjwtmzWS2K3sCN3Z_PweRNL_GMK2UrxOzvn_RAucBtQI8ChggFgXTkJGXWcahWTpGBWTTr0scig-6OaghvUS4wzWEO4banptXkB1L1KcLgYV6XmKJ_pO_04g1KimvXgOSsWPoUUxxD6flmxCRD0xCKV_IOL6ecD4l898aahn6xJ_-htCMqvqtv2iJAM_SuOsET2AvHlibVHMgvTsb5SpV4Ejj-N4hhgC9kIZUOaw7If0fQH4ElX2e2NXKwS1haVgczlo5p9e80RZnwGRDEgtyWnhceE1fcesbJf-EuCuAKDXrMmUeoZkR7O7AjrveZddBWp1sWj2PYWR6IBEuWuqmG9cK6oc7AJKJplMYR_KhnoNxh29trDnSvVKv7VGgaIwb1Lgfk4o1cRurC80l5JYGIDGi6HWul4siMoWnc6upNSQ9Zqkl_g1OJXe-flQNIgJjewowexXWFxIlPJQIK_s4xetVqByowq9Re4Xz1CfNH9xgfIpWudMRLi_ACZoIt26vlsvlxWy89d4s1k2BrCpzix5H4W2LldQuDUnf9OjwGLQan7TrJn-m3dI6se2UKiagHM4P9fQauYF9WDK8mG5BoO7v2smJPqyfYfQlsv5Uomm1uTpDfwdplRsG4hw6MBLedoFpHtOcxzJck3K-dHPk02q8piyBCdqILCUZlxqH0oLPH3dHN_A29ilX5zSpL_Oatjgog643pto56VhpcGMnYKG2S78FC-ani-btEbCAbJF5tiue042LQehaPQSMmze74FODJxC_IQP9Kde6tcGLMXlJqKASkDZ9MUUGlycAa6wzbeO_xRpuW9iKEF8N3XC4pY_WPRUTf3qPYvC6iksAh1Xvq3WB5ZG9PLDBsN17IZubwoyWZ4D8FE70aysS7aqMzUNos_gAn6JxCaXrpVyuQYcqdN2urG96Eg0w8MJ1MHlq0rbgeCHWbb4UNRfHjZjQT_WvpsLiXIgMWkg2ksw7ZNet6JFTqtjuZ4DKyp_xEuAM_FrgLJ1vztdw3t0lEii__dFq1p223klRujKYBZekDvcXgvgEUqOMXJj8DQ9o2B3hBJAxn4Dxw1yxPLrHW5thP0OGLRk4x3kjBZo-6RJY8gILYhBIpWsIdSmCQ=w1366-h643",
                        "3.acc",
                        "Tejiendo para el frío",
                        3,
                        3,
                        5,
                        "2024-05-12",
                        "Pintura",
                        "Pintura de luz, larga exposición, apilado",
                        "En esta pintura, una llama antropomórfica aparece tejiendo en medio de un paisaje andino. Las montañas nevadas y las pequeñas casas de tejados rojos en el fondo crean un ambiente sereno y tradicional. La llama, con su lana blanca y mirada serena, sostiene con destreza el tejido de colores vibrantes, reflejando una conexión profunda con la cultura y las artesanías del altiplano. La composición captura la esencia de la vida rural y la tranquilidad del entorno natural."
                ));
            }





        });
    }
}
