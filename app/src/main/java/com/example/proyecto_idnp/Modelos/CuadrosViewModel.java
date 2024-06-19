package com.example.proyecto_idnp.Modelos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto_idnp.Adaptadores.Cuadro;

import java.util.ArrayList;
import java.util.List;

public class CuadrosViewModel extends ViewModel {
    private MutableLiveData<List<Cuadro>> cuadrosLiveData;
    private MutableLiveData<Cuadro> cuadroSeleccionado;
    private List<Cuadro> listaCuadros;

    public CuadrosViewModel() {
        cuadrosLiveData = new MutableLiveData<>();
        cuadroSeleccionado = new MutableLiveData<>();
        loadCuadros();
    }
    public LiveData<List<Cuadro>> getCuadrosLiveData() {
        return cuadrosLiveData;
    }

    public LiveData<Cuadro> getCuadroSeleccionado() {
        return cuadroSeleccionado;
    }

    public void setCuadroSeleccionado(Cuadro cuadro) {
        cuadroSeleccionado.setValue(cuadro);
    }

    public void setCuadroSeleccionadoPorId(int id) {
        Cuadro cuadro = getCuadroPorId(id);
        cuadroSeleccionado.setValue(cuadro);
    }
    private Cuadro getCuadroPorId(int id) {
        if (listaCuadros != null) {
            for (Cuadro cuadro : listaCuadros) {
                if (cuadro.getId() == id) {
                    return cuadro;
                }
            }
        }
        return null;
    }
    private void loadCuadros() {
        listaCuadros = new ArrayList<>();

        listaCuadros.add(new Cuadro(
                1,
                "LA YAKANA EN ANTAKARI",
                "DIMENSIONES: 50 x 30 cm.\n" +
                        "TÉCNICA: Pintura de luz, larga exposición, apilado\n" +
                        "AÑO: 2024",
                "https://ccunsa.org.pe/wp-content/uploads/2024/04/89.jpg",
                "expo"
        ));
        listaCuadros.add(new Cuadro(
                2,
                "SOGAY",
                "DIMENSIONES:  30 x 45 cm.\n" +
                        "TÉCNICA: Larga exposiciòn\n " +
                        "AÑO: 2023",
                "https://ccunsa.org.pe/wp-content/uploads/2024/04/85.jpg",
                "expo"
        ));
        listaCuadros.add(new Cuadro(
                3,
                "POCSI",
                "DIMENSIONES: 30 x 45 cm.\n" +
                        "TÉCNICA: Pintura de luz, y larga exposición\n" +
                        "AÑO: 2022",
                "https://ccunsa.org.pe/wp-content/uploads/2024/04/91.jpg",
                "expo"
        ));

        Log.d("SharedViewModel", "Lista de cuadros inicializada con " + listaCuadros.size() + " elementos.");

        cuadrosLiveData.setValue(listaCuadros);
    }
}