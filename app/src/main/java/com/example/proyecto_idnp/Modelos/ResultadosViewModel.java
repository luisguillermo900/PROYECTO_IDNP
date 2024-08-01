package com.example.proyecto_idnp.Modelos;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto_idnp.Entidades.ResultadoFiltro;

import java.util.ArrayList;
import java.util.List;

public class ResultadosViewModel extends ViewModel {
    private MutableLiveData<List<ResultadoFiltro>> resultadosLiveData;
    private MutableLiveData<ResultadoFiltro> resultadoSeleccionado;
    private String filtroSeleccionado = "";
    private List<ResultadoFiltro> resultados;
    public ResultadosViewModel(){
        resultadosLiveData = new MutableLiveData<>();
        resultadoSeleccionado = new MutableLiveData<>();
    }
    public LiveData<List<ResultadoFiltro>> getResultadosLiveData(){
        switch (filtroSeleccionado) {
            case "Exposiciones":
                cargarConsultaPorExposiciones();
                break;
            case "Autores":
                cargarConsultaPorAutores();
                break;
            case "Tipos":
                break;
            case "Galerias":
                break;
            default:
                Log.d(TAG, "Opcion Invalida");
        }
        return resultadosLiveData;
    }
    public LiveData<ResultadoFiltro> getResultadoSeleccionado(){
        return resultadoSeleccionado;
    }
    public void setResultadoSeleccionado(ResultadoFiltro resultado) {
        resultadoSeleccionado.setValue(resultado);
    }
    public void setfiltroSeleccionado(String seleccionado) {
        filtroSeleccionado = seleccionado;
    }
    public String getfiltroSeleccionado() {
        return filtroSeleccionado;
    }

    private void cargarResultados(){
        resultados = new ArrayList<>();
        resultados.add(new ResultadoFiltro("https://scontent.faqp1-1.fna.fbcdn.net/v/t39.30808-6/447519333_894661172658611_6308906618339348145_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeE46AM9Amk18VAXc66N20CzQ6SwSFdQdTNDpLBIV1B1M9wMaAoQNgIGVYc8Rg_Ip3Vef8EQKhLp_aq8WDjMNmbX&_nc_ohc=RUFRL5SvpyoQ7kNvgHH7BFU&_nc_ht=scontent.faqp1-1.fna&oh=00_AYDvcs2Z3vUpeK1590cSVPUv_mAjEes3eSPCPia9nGKyHg&oe=66B0E723","La otra ciudad"));
        resultados.add(new ResultadoFiltro("https://scontent.faqp3-1.fna.fbcdn.net/v/t39.30808-6/442435875_888610933263635_6870633362810967070_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeEa4YOx_CX-V2ynwj91Kd7ZHc8etd2s8xcdzx613azzF7huSFb6YpzW49X8GLy5b1Ho0lkjh-hFpxRiuB1-MVJ9&_nc_ohc=2PJ6xiv-b2sQ7kNvgHlvNe0&_nc_ht=scontent.faqp3-1.fna&oh=00_AYDSUxuFQqLLMmf5sk3-wkEKjysjWuGlZIs1AUMWIlH8KQ&oe=66B0F81C","Silencios revelados"));
        resultados.add(new ResultadoFiltro("https://scontent.faqp3-1.fna.fbcdn.net/v/t39.30808-6/447677894_894660599325335_486979158550427425_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeH9LXqpxu4VVqx-zLjooB35UKyGONi0DRFQrIY42LQNESb3NeFdgKPDnw3frfyf5jEIEa2efR21I_gt-gZFZnxH&_nc_ohc=AhffGP2aWpgQ7kNvgHrSMhh&_nc_ht=scontent.faqp3-1.fna&gid=ARTY0714EFk0xpDsC8QRk0F&oh=00_AYBDVAL4pRDZnSHsCTLZH2Xl2SXfNTtJwX6hELx7nsJobg&oe=66B0EB5B","La otra ciudad"));
        resultados.add(new ResultadoFiltro("https://scontent.faqp3-1.fna.fbcdn.net/v/t39.30808-6/442435875_888610933263635_6870633362810967070_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeEa4YOx_CX-V2ynwj91Kd7ZHc8etd2s8xcdzx613azzF7huSFb6YpzW49X8GLy5b1Ho0lkjh-hFpxRiuB1-MVJ9&_nc_ohc=2PJ6xiv-b2sQ7kNvgHlvNe0&_nc_ht=scontent.faqp3-1.fna&oh=00_AYDSUxuFQqLLMmf5sk3-wkEKjysjWuGlZIs1AUMWIlH8KQ&oe=66B0F81C","La otra ciudad"));
        Log.d("SharedViewModel", "Lista de resultados inicializada con " + resultados.size() + " elementos.");

        resultadosLiveData.setValue(resultados);
    }

    public void cargarConsultaPorAutores(){
        /*
        Se invoca el metodo de consulta desde el DAO
        *SQL*
        SELECT nombres || ' ' || apellidos AS nombre_completo, url_imagen
        FROM Autor
        Deberia retornar una Lista con objetos ResultadoFiltro
        */
        resultados = new ArrayList<>();
        resultados.add(new ResultadoFiltro("https://bptfotografia.com/wp-content/uploads/2021/08/foto-de-retrato-tipos.jpg","Juan Perez"));
        resultados.add(new ResultadoFiltro("https://img.freepik.com/foto-gratis/joven-barbudo-camisa_273609-5938.jpg?w=740&t=st=1721539590~exp=1721540190~hmac=f97868024806588bab30929224ef3a5ba87568eda000c14cb54bf6ec56335a20","Jose Castro"));
        resultados.add(new ResultadoFiltro("https://previews.123rf.com/images/rawpixel/rawpixel1704/rawpixel170441704/76561515-retrato-de-personas-estudio-disparar-con-expresi%C3%B3n-de-cara-sonriente.jpg","Maria Juana"));
        resultados.add(new ResultadoFiltro("https://pixnio.com/free-images/2017/11/30/2017-11-30-18-37-25-576x864.jpg","Lucia Flores"));
        resultados.add(new ResultadoFiltro("https://pixnio.com/free-images/2017/11/30/2017-11-30-18-37-25-576x864.jpg","Lucia Flores"));
        Log.d("SharedViewModel", "Lista de autores de " + resultados.size() + " elementos.");

        resultadosLiveData.setValue(resultados);
    }
    public void cargarConsultaPorExposiciones(){
        /*
        Se invoca el metodo de consulta desde el DAO
        *SQL*
        SELECT nombre, imagen_url FROM Exposicion;
        Deberia retornar una Lista con objetos ResultadoFiltro
        */
        resultados = new ArrayList<>();
        resultados.add(new ResultadoFiltro("https://scontent.faqp1-1.fna.fbcdn.net/v/t39.30808-6/447519333_894661172658611_6308906618339348145_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeE46AM9Amk18VAXc66N20CzQ6SwSFdQdTNDpLBIV1B1M9wMaAoQNgIGVYc8Rg_Ip3Vef8EQKhLp_aq8WDjMNmbX&_nc_ohc=RUFRL5SvpyoQ7kNvgHH7BFU&_nc_ht=scontent.faqp1-1.fna&oh=00_AYDvcs2Z3vUpeK1590cSVPUv_mAjEes3eSPCPia9nGKyHg&oe=66B0E723","La otra ciudad"));
        resultados.add(new ResultadoFiltro("https://scontent.faqp3-1.fna.fbcdn.net/v/t39.30808-6/442435875_888610933263635_6870633362810967070_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeEa4YOx_CX-V2ynwj91Kd7ZHc8etd2s8xcdzx613azzF7huSFb6YpzW49X8GLy5b1Ho0lkjh-hFpxRiuB1-MVJ9&_nc_ohc=2PJ6xiv-b2sQ7kNvgHlvNe0&_nc_ht=scontent.faqp3-1.fna&oh=00_AYDSUxuFQqLLMmf5sk3-wkEKjysjWuGlZIs1AUMWIlH8KQ&oe=66B0F81C","Silencios revelados"));
        resultados.add(new ResultadoFiltro("https://scontent.faqp3-1.fna.fbcdn.net/v/t39.30808-6/447677894_894660599325335_486979158550427425_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeH9LXqpxu4VVqx-zLjooB35UKyGONi0DRFQrIY42LQNESb3NeFdgKPDnw3frfyf5jEIEa2efR21I_gt-gZFZnxH&_nc_ohc=AhffGP2aWpgQ7kNvgHrSMhh&_nc_ht=scontent.faqp3-1.fna&gid=ARTY0714EFk0xpDsC8QRk0F&oh=00_AYBDVAL4pRDZnSHsCTLZH2Xl2SXfNTtJwX6hELx7nsJobg&oe=66B0EB5B","La otra ciudad"));
        resultados.add(new ResultadoFiltro("https://scontent.faqp3-1.fna.fbcdn.net/v/t39.30808-6/442435875_888610933263635_6870633362810967070_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeEa4YOx_CX-V2ynwj91Kd7ZHc8etd2s8xcdzx613azzF7huSFb6YpzW49X8GLy5b1Ho0lkjh-hFpxRiuB1-MVJ9&_nc_ohc=2PJ6xiv-b2sQ7kNvgHlvNe0&_nc_ht=scontent.faqp3-1.fna&oh=00_AYDSUxuFQqLLMmf5sk3-wkEKjysjWuGlZIs1AUMWIlH8KQ&oe=66B0F81C","La otra ciudad"));
        Log.d("SharedViewModel", "Lista de exposiciones de " + resultados.size() + " elementos.");
        resultadosLiveData.setValue(resultados);
    }
    public void cargarConsultaPorGalerias(){
        /*
        Se invoca el metodo de consulta desde el DAO
        *SQL*
        SELECT Galeria.nombre, Exposicion.imagen_url
        FROM Galeria
        INNER JOIN Exposicion ON Galeria.id_exposicion=Exposicion.id;
        Deberia retornar una Lista con objetos ResultadoFiltro
        */
        Log.d("SharedViewModel", "Lista de galerias de " + resultados.size() + " elementos.");
        resultadosLiveData.setValue(resultados);
    }
    public void cargarConsultaPorTipo(){
        /*
        Se invoca el metodo de consulta desde el DAO
        *SQL*
        SELECT tipo, url_imagen FROM ObraDeArte GROUP BY tipo;
        Deberia retornar una Lista con objetos ResultadoFiltro
        */
        Log.d("SharedViewModel", "Lista de tipos de " + resultados.size() + " elementos.");
        resultadosLiveData.setValue(resultados);
    }
}