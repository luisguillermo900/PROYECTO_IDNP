package com.example.proyecto_idnp.Modelos;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.proyecto_idnp.Abstract.AppDatabase;
import com.example.proyecto_idnp.Dao.DaoAutor;
import com.example.proyecto_idnp.Dao.DaoExposicion;
import com.example.proyecto_idnp.Dao.DaoGaleria;
import com.example.proyecto_idnp.Dao.DaoObra;
import com.example.proyecto_idnp.Entidades.ResultadoFiltro;

import java.util.ArrayList;
import java.util.List;

public class ResultadosViewModel extends AndroidViewModel {
    private AppDatabase appBD;
    private MutableLiveData<List<ResultadoFiltro>> resultadosLiveData;
    private MutableLiveData<ResultadoFiltro> resultadoSeleccionado;
    private String filtroSeleccionado = "";
    private List<ResultadoFiltro> resultados;
    private DaoGaleria daoGaleria;
    private DaoAutor daoAutor;
    private DaoExposicion daoExposicion;
    private DaoObra daoObra;

    public ResultadosViewModel(@NonNull Application application) {
        super(application);
        resultadosLiveData = new MutableLiveData<>();
        resultadoSeleccionado = new MutableLiveData<>();
        appBD = AppDatabase.getInstance(application);
        daoGaleria = appBD.daoGaleria();
        daoAutor = appBD.daoAuthor();
        daoExposicion = appBD.daoExposicion();
        daoObra = appBD.daoObra();
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
        resultadosLiveData.setValue(daoAutor.filtrarAutores());
    }
    public void cargarConsultaPorExposiciones(){
        resultadosLiveData.setValue(daoExposicion.filtrarExposiciones());
    }
    public void cargarConsultaPorGalerias(){
        resultadosLiveData.setValue(daoGaleria.filtrarGalerias());
    }
    public void cargarConsultaPorTipo(){
        resultadosLiveData.setValue(daoObra.filtrarTipos());
    }
}