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
        resultados.add(new ResultadoFiltro("https://ccunsa.org.pe/wp-content/uploads/2024/06/167.jpg","La otra ciudad"));
        resultados.add(new ResultadoFiltro("https://ccunsa.org.pe/wp-content/uploads/2024/06/9-1.jpg","Silencios revelados"));
        resultados.add(new ResultadoFiltro("https://ccunsa.org.pe/wp-content/uploads/2024/06/167.jpg","La otra ciudad"));
        resultados.add(new ResultadoFiltro("https://ccunsa.org.pe/wp-content/uploads/2024/06/167.jpg","La otra ciudad"));
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