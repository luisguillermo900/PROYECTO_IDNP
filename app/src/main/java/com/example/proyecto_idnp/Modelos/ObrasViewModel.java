package com.example.proyecto_idnp.Modelos;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto_idnp.Abstract.AppDatabase;
import com.example.proyecto_idnp.Dao.DaoObra;
import com.example.proyecto_idnp.Entidades.ObraDeArte;

import java.util.ArrayList;
import java.util.List;

public class ObrasViewModel extends AndroidViewModel {
    private final MutableLiveData<List<ObraDeArte>> obrasList = new MutableLiveData<>();
    private AppDatabase appBD;
    private DaoObra daoObra;
    private MutableLiveData<List<ObraDeArte>> obrasLiveData;
    private MutableLiveData<ObraDeArte> obraSeleccionada;
    private List<ObraDeArte> listaObras;

    public ObrasViewModel(@NonNull Application application) {
        super(application);
        obrasLiveData = new MutableLiveData<>();
        obraSeleccionada = new MutableLiveData<>();
        appBD = AppDatabase.getInstance(application);
        daoObra = appBD.daoObra();
    }
    public LiveData<List<ObraDeArte>> getObrasLiveData() {
        return obrasLiveData;
    }

    public LiveData<ObraDeArte> getObraSeleccionada() {
        return obraSeleccionada;
    }

    public void setObraSeleccionada(ObraDeArte obra) {
        obraSeleccionada.setValue(obra);
    }

    public void setObraSeleccionadaPorId(int id) {
        obraSeleccionada.setValue(daoObra.obtenerObra(id));
    }

    public LiveData<List<ObraDeArte>> getObrasList() {
        return obrasList;
    }

    public void setObrasList(List<ObraDeArte> obras) {
        obrasList.setValue(obras);
    }

    private ObraDeArte getObraPorId(int id) {
        return daoObra.obtenerObra(id);
    }

    public void cargarObrasPorTipo(String tipo){
        obrasLiveData.setValue(daoObra.cargarObrasPorTipo(tipo));
    }
    public void cargarObrasPorAutor(String autor){
        obrasLiveData.setValue(daoObra.cargarObrasPorAutor(autor));
    }
    public void cargarObrasPorGaleria(String galeria){
        obrasLiveData.setValue(daoObra.cargarObrasPorGaleria(galeria));
    }
    public void cargarObrasPorExposicion(String exposicion){
        Log.d("ObrasViewModel", daoObra.cargarObrasPorExposicion(exposicion).toString());
        obrasLiveData.setValue(daoObra.cargarObrasPorExposicion(exposicion));
        Log.d("ObrasViewModel", obrasLiveData.toString());
        mostrarObrasLiveData();
    }
    public void mostrarObrasLiveData(){
        for(int i=0; i< obrasLiveData.getValue().size(); i++){
            Log.d("ObrasViewModel", obrasLiveData.getValue().get(i).getUrlImagen());
        }
    }
}