package com.example.proyecto_idnp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyecto_idnp.Abstract.AppDatabase;
import com.example.proyecto_idnp.Adaptadores.AdaptadorObra;
import com.example.proyecto_idnp.Adaptadores.OnObraClickListener;
import com.example.proyecto_idnp.Dao.DaoAutor;
import com.example.proyecto_idnp.Dao.DaoExposicion;
import com.example.proyecto_idnp.Dao.DaoObra;
import com.example.proyecto_idnp.Entidades.Exposicion;
import com.example.proyecto_idnp.Entidades.ObraDeArte;
import com.example.proyecto_idnp.Entidades.ResultadoFiltro;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.Modelos.ResultadosViewModel;
import com.example.proyecto_idnp.R;

public class DetalleExposicionFragment extends Fragment implements OnObraClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerListaObras;
    private AdaptadorObra adaptadorObras;
    private ObrasViewModel obrasModel;
    private ResultadosViewModel resultadosModel;
    private String nombreFiltro = "";
    private ResultadoFiltro resultadoSeleccionado;
    AppDatabase db = AppDatabase.getInstance(getContext());
    DaoExposicion daoExposicion = db.daoExposicion();

    private String mParam1;
    private String mParam2;

    public DetalleExposicionFragment() {
        // Required empty public constructor
    }

    public static DetalleExposicionFragment newInstance(String param1, String param2) {
        DetalleExposicionFragment fragment = new DetalleExposicionFragment();
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
        View view = inflater.inflate(R.layout.fragment_detalle_exposicion, container, false);
        TextView txtTituloExposicion = view.findViewById(R.id.txtTituloExposicion);
        TextView txtFechaExposicion = view.findViewById(R.id.txtFechaExposicion);
        ImageView imgFotoExposicion = view.findViewById(R.id.imgFotoExposicion);
        ImageView btnExpoVolver = view.findViewById(R.id.btnExpoVolver);

        resultadosModel = new ViewModelProvider(requireActivity()).get(ResultadosViewModel.class);
        obrasModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);
        resultadoSeleccionado = resultadosModel.getResultadoSeleccionado().getValue();
        nombreFiltro = resultadoSeleccionado.getNombre();

        // Observar el resultado seleccionado y actualizar la UI
        resultadosModel.getResultadoSeleccionado().observe(getViewLifecycleOwner(), resultado -> {
            Exposicion expoObtenida = consultaExposicion(resultadoSeleccionado);
            if (resultado != null) {
                txtTituloExposicion.setText(expoObtenida.getNombre());
                Glide.with(getContext())
                        .load(expoObtenida.getUrlImagen())
                        .centerCrop()
                        .into(imgFotoExposicion);
                txtFechaExposicion.setText(expoObtenida.getFecha());
            }
        });

        btnExpoVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack(); // Navega al fragmento anterior en el back stack
                }
            }
        });

        obrasModel.cargarObrasPorExposicion(nombreFiltro);
        recyclerListaObras = view.findViewById(R.id.recyclerObrasGenerico);
        recyclerListaObras.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("DetalleExposicionFragment", obrasModel.getObrasLiveData().getValue().toString());
        adaptadorObras = new AdaptadorObra(obrasModel.getObrasLiveData().getValue(),getContext(),this);
        recyclerListaObras.setAdapter(adaptadorObras);
        return view;
    }
    @Override
    public void onObraClick(ObraDeArte obra) {
        obrasModel.setObraSeleccionada(obra);
        //Cargar fragment detalle (ya se carga al usar la funcion setObraSeleccionada)
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.contenedorFragments, DetalleObraFragment.class, null)
                .addToBackStack(null)
                .commit();
    }

    public Exposicion consultaExposicion(ResultadoFiltro resultado){
        Exposicion expoConsultada = daoExposicion.getExposicionPorNombre(resultado.getNombre());;
        return expoConsultada;
    }
}