package com.example.proyecto_idnp.Modelos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto_idnp.Entidades.ObraDeArte;

import java.util.ArrayList;
import java.util.List;

public class ObrasViewModel extends ViewModel {
    private MutableLiveData<List<ObraDeArte>> obrasLiveData;
    private MutableLiveData<ObraDeArte> obraSeleccionada;
    private MutableLiveData<ObraDeArte> closePicture;
    private List<ObraDeArte> listaObras;

    public ObrasViewModel() {
        obrasLiveData = new MutableLiveData<>();
        obraSeleccionada = new MutableLiveData<>();
        closePicture = new MutableLiveData<>();
        loadObras();
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
        ObraDeArte obra = getObraPorId(id);
        obraSeleccionada.setValue(obra);
    }

    public void setClosePictureById(int id) {
        ObraDeArte obra = getObraPorId(id);
        closePicture.setValue(obra);
    }

    public LiveData<ObraDeArte> getClosePicture(){
        return closePicture;
    }

    private ObraDeArte getObraPorId(int id) {
        if (listaObras != null) {
            for (ObraDeArte obra : listaObras) {
                if (obra.getId() == id) {
                    return obra;
                }
            }
        }
        return null;
    }
    private void loadObras() {
        listaObras = new ArrayList<>();

        listaObras.add(new ObraDeArte(
                1,
                "https://ccunsa.org.pe/wp-content/uploads/2024/04/89.jpg",
                "LA YAKANA EN ANTAKARI",
                1,
                1,
                1,
                "2024-05-12",
                "Fotografia",
                "Pintura de luz, larga exposición, apilado",
                "DIMENSIONES: 50 x 30 cm.\n"
        ));
        listaObras.add(new ObraDeArte(
                2,
                "https://ccunsa.org.pe/wp-content/uploads/2024/04/85.jpg",
                "SOGAY",
                1,
                1,
                1,
                "2024-05-12",
                "Fotografia",
                "Larga exposiciòn",
                "DIMENSIONES:  30 x 45 cm.\n"
        ));
        listaObras.add(new ObraDeArte(
                2,
                "https://ccunsa.org.pe/wp-content/uploads/2024/04/85.jpg",
                "POCSI",
                1,
                1,
                1,
                "2024-05-14",
                "Fotografia",
                "Pintura de luz, y larga exposición",
                "DIMENSIONES: 30 x 45 cm.\n"
        ));

        Log.d("SharedViewModel", "Lista de cuadros inicializada con " + listaObras.size() + " elementos.");

        obrasLiveData.setValue(listaObras);
    }
}