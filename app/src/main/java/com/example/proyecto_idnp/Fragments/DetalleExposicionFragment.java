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

import com.example.proyecto_idnp.Adaptadores.AdaptadorObra;
import com.example.proyecto_idnp.Adaptadores.OnObraClickListener;
import com.example.proyecto_idnp.Entidades.ObraDeArte;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleExposicionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleExposicionFragment extends Fragment implements OnObraClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerListaObras;
    private AdaptadorObra adaptadorObras;
    private ObrasViewModel obrasModel;

    // TODO: Rename and change types of parameters
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
        obrasModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);
        recyclerListaObras = view.findViewById(R.id.recyclerFiltros);
        recyclerListaObras.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptadorObras = new AdaptadorObra(obrasModel.getObrasLiveData().getValue(),getContext(),this);
        recyclerListaObras.setAdapter(adaptadorObras);
        Log.d("AdaptadorCuadro", "Adaptador configurado y asignado al RecyclerView.");
        return view;
    }
    @Override
    public void onObraClick(ObraDeArte obra) {
        obrasModel.setObraSeleccionada(obra);
        //Cargar fragment detalle (ya se carga al usar la funcion setObraSeleccionada)
        /*FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.contenedorFragments, DetalleObraFragment.class, null)
                .addToBackStack(null)
                .commit();*/
    }
}