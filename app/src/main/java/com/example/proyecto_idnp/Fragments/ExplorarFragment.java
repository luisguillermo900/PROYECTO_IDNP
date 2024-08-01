package com.example.proyecto_idnp.Fragments;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyecto_idnp.Adaptadores.AdaptadorResultado;
import com.example.proyecto_idnp.Adaptadores.OnResultadoClickListener;
import com.example.proyecto_idnp.Entidades.ResultadoFiltro;
import com.example.proyecto_idnp.Modelos.ResultadosViewModel;
import com.example.proyecto_idnp.R;

import java.util.List;

public class ExplorarFragment extends Fragment implements OnResultadoClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerListaResultados;
    private ResultadosViewModel resultadosModel;
    private AdaptadorResultado adaptadorResultados;
    private List<ResultadoFiltro> resultados;
    private Button btnFiltroAutor;
    private Button btnFiltroTipo;
    private Button btnFiltroExpo;
    private Button btnFiltroGaleria;
    private String filtroSeleccionado;

    private String mParam1;
    private String mParam2;

    public ExplorarFragment() {
        // Required empty public constructor
    }

    public static ExplorarFragment newInstance(String param1, String param2) {
        ExplorarFragment fragment = new ExplorarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explorar, container, false);
        btnFiltroAutor = view.findViewById(R.id.btnFiltroAutor);
        btnFiltroTipo = view.findViewById(R.id.btnFiltroTipo);
        btnFiltroExpo = view.findViewById(R.id.btnFiltroExpo);
        btnFiltroGaleria = view.findViewById(R.id.btnFiltroGaleria);

        //Cargando el LiveModel de resultados
        resultadosModel = new ViewModelProvider(requireActivity()).get(ResultadosViewModel.class);
        recyclerListaResultados = view.findViewById(R.id.recyclerObrasGenerico);
        recyclerListaResultados.setLayoutManager(new GridLayoutManager(getContext(),3));

        //Cargando datos de resultados del viewmodel al adaptador de acuerdo al filtro seleccionado
        resultados = resultadosModel.getResultadosLiveData().getValue();
        adaptadorResultados = new AdaptadorResultado(resultados,getContext(),this);
        recyclerListaResultados.setAdapter(adaptadorResultados);
        Log.d("AdaptadorResultado", "Adaptador configurado y asignado al RecyclerView.");
        //Asignando listeners para los botones de filtro
        btnFiltroExpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultadosModel.setfiltroSeleccionado("Exposiciones");
                actualizarDatos();
            }
        });

        btnFiltroAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultadosModel.setfiltroSeleccionado("Autores");
                actualizarDatos();
            }
        });

        btnFiltroTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultadosModel.setfiltroSeleccionado("Tipos");
                actualizarDatos();
            }
        });

        btnFiltroGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultadosModel.setfiltroSeleccionado("Galerias");
                actualizarDatos();
            }
        });
        return view;
    }
    @Override
    public void onResultadoClick(ResultadoFiltro resultado) {
        Fragment fragmentoDetalle = null;
        filtroSeleccionado = resultadosModel.getfiltroSeleccionado();
        resultadosModel.setResultadoSeleccionado(resultado);
        if(filtroSeleccionado.equals("Exposicion")){
            fragmentoDetalle = new DetalleExposicionFragment();
        } else {
            fragmentoDetalle = new DetalleGenericoFragment();
        }
        //Cargar fragment detalle
        if (fragmentoDetalle != null) {
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedorFragments, fragmentoDetalle, null)
                    .addToBackStack(null)
                    .commit();
        } else {
            Toast.makeText(getContext(), "No se pudo cargar el fragmento", Toast.LENGTH_SHORT).show();
        }

    }

    private void filtrarPorAutor(){
        //Se va a ejecutar una consulta SQL y actualizar el RecyclerView
        resultadosModel.cargarConsultaPorAutores();
        actualizarDatos();
    }
    private void filtrarPorTipo(){
        resultadosModel.cargarConsultaPorTipo();
        actualizarDatos();
    }
    private void filtrarPorExposicion(){
        resultadosModel.cargarConsultaPorExposiciones();
        actualizarDatos();
    }
    private void filtrarPorGaleria(){
        resultadosModel.cargarConsultaPorGalerias();
        actualizarDatos();
    }
    private void actualizarDatos(){
        resultados.clear();
        resultados.addAll(resultadosModel.getResultadosLiveData().getValue());
        adaptadorResultados.notifyDataSetChanged();
    }
}