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
import com.example.proyecto_idnp.Entidades.ObraDeArte;
import com.example.proyecto_idnp.Adaptadores.OnObraClickListener;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.R;

public class ObrasFragment extends Fragment implements OnObraClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerListaObras;
    private AdaptadorObra adaptadorObra;
    private ObrasViewModel obrasModel;

    private String mParam1;
    private String mParam2;

    public ObrasFragment() {
        // Required empty public constructor
    }

    public static ObrasFragment newInstance(String param1, String param2) {
        ObrasFragment fragment = new ObrasFragment();
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
        View view = inflater.inflate(R.layout.fragment_obras, container, false);
        obrasModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);
        recyclerListaObras = view.findViewById(R.id.recyclerListaObras);
        recyclerListaObras.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptadorObra = new AdaptadorObra(obrasModel.getObrasLiveData().getValue(),getContext(),this);
        recyclerListaObras.setAdapter(adaptadorObra);
        Log.d("AdaptadorObra", "Adaptador configurado y asignado al RecyclerView.");
        return view;
    }
    @Override
    public void onObraClick(ObraDeArte obra) {
        obrasModel.setObraSeleccionada(obra);
        //Cargar fragment detalle
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, DetalleObraFragment.class, null)
                .addToBackStack(null)
                .commit();
    }
}