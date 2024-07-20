package com.example.proyecto_idnp.Modelos;

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
    private List<ResultadoFiltro> resultados;
    public ResultadosViewModel(){
        resultadosLiveData = new MutableLiveData<>();
        resultadoSeleccionado = new MutableLiveData<>();
        cargarResultados();
    }
    public LiveData<List<ResultadoFiltro>> getResultadosLiveData(){
        return resultadosLiveData;
    }
    public LiveData<ResultadoFiltro> getResultadoSeleccionado(){
        return resultadoSeleccionado;
    }
    public void setResultadoSeleccionado(ResultadoFiltro resultado) {
        resultadoSeleccionado.setValue(resultado);
    }

    public void setResultadoSeleccionadoporNombre(String nombre) {
        ResultadoFiltro resultado = getResultadoPorNombre(nombre);
        resultadoSeleccionado.setValue(resultado);
    }

    private ResultadoFiltro getResultadoPorNombre(String nombre) {
        if (resultados != null) {
            for (ResultadoFiltro resultado : resultados) {
                if (resultado.getNombre().equals(nombre)) {
                    return resultado;
                }
            }
        }
        return null;
    }
    private void cargarResultados(){
        resultados = new ArrayList<>();
        resultados.add(new ResultadoFiltro("https://ccunsa.org.pe/wp-content/uploads/2024/06/167.jpg","La otra ciudad"));
        resultados.add(new ResultadoFiltro("https://ccunsa.org.pe/wp-content/uploads/2024/06/9-1.jpg","Silencios revelados"));
        resultados.add(new ResultadoFiltro("https://ccunsa.org.pe/wp-content/uploads/2024/06/167.jpg","La otra ciudad"));
        resultados.add(new ResultadoFiltro("https://ccunsa.org.pe/wp-content/uploads/2024/06/167.jpg","La otra ciudad"));
        Log.d("SharedViewModel", "Lista de resultados inicializada con " + resultados.size() + " elementos.");

        resultadosLiveData.setValue(resultados);
    }
}