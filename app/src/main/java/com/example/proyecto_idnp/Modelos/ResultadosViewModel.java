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