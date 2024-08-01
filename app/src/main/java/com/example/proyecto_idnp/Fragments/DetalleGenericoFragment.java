package com.example.proyecto_idnp.Fragments;

import static android.content.ContentValues.TAG;

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
import com.example.proyecto_idnp.Adaptadores.AdaptadorObra;
import com.example.proyecto_idnp.Adaptadores.OnObraClickListener;
import com.example.proyecto_idnp.Entidades.ObraDeArte;
import com.example.proyecto_idnp.Entidades.ResultadoFiltro;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.Modelos.ResultadosViewModel;
import com.example.proyecto_idnp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleGenericoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleGenericoFragment extends Fragment implements OnObraClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerListaObras;
    private AdaptadorObra adaptadorObras;
    private ObrasViewModel obrasModel;
    private ResultadosViewModel resultadosModel;
    private ResultadoFiltro resultadoSeleccionado;

    private String mParam1;
    private String mParam2;

    public DetalleGenericoFragment() {
        // Required empty public constructor
    }

    public static DetalleGenericoFragment newInstance(String param1, String param2) {
        DetalleGenericoFragment fragment = new DetalleGenericoFragment();
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
        View view = inflater.inflate(R.layout.fragment_detalle_generico, container, false);
        ImageView imgFotoGenerico = view.findViewById(R.id.imgFotoGenerico);
        TextView txtTituloGenerico = view.findViewById(R.id.txtTituloGenerico);
        ImageView btnGenVolver = view.findViewById(R.id.btnGenVolver);

        resultadosModel = new ViewModelProvider(requireActivity()).get(ResultadosViewModel.class);
        obrasModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);

        // Observar el resultado seleccionado y actualizar la UI
        resultadosModel.getResultadoSeleccionado().observe(getViewLifecycleOwner(), resultado -> {
            if (resultado != null) {
                txtTituloGenerico.setText(resultado.getNombre());
                Glide.with(getContext())
                        .load(resultado.getUrlImagen())
                        .centerCrop()
                        .into(imgFotoGenerico);
            }
        });

        btnGenVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack(); // Navega al fragmento anterior en el back stack
                }
            }
        });

        resultadoSeleccionado = resultadosModel.getResultadoSeleccionado().getValue();
        recyclerListaObras = view.findViewById(R.id.recyclerObrasGenerico);
        recyclerListaObras.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptadorObras = new AdaptadorObra(obrasModel.getObrasLiveData().getValue(),getContext(),this);
        recyclerListaObras.setAdapter(adaptadorObras);
        Log.d("AdaptadorCuadro", "Adaptador configurado y asignado al RecyclerView.");

        return view;
    }

    public void onObraClick(ObraDeArte obra) {
        obrasModel.setObraSeleccionada(obra);
        //Cargar fragment detalle (ya se carga al usar la funcion setObraSeleccionada)
        Log.d(TAG, "Pulsar sobre obra");
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.contenedorFragments, DetalleObraFragment.class, null)
                .addToBackStack(null)
                .commit();
    }
}