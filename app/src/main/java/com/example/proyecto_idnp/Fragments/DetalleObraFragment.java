package com.example.proyecto_idnp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.R;

public class DetalleObraFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ObrasViewModel obrasModel;

    public DetalleObraFragment() {
        // Required empty public constructor
    }

    public static DetalleObraFragment newInstance(String param1, String param2) {
        DetalleObraFragment fragment = new DetalleObraFragment();
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
        View view = inflater.inflate(R.layout.fragment_detalle_obra, container, false);
        TextView txtDetObraTitulo = view.findViewById(R.id.txtDetObraTitulo);
        ImageView imgDetObraFoto = view.findViewById(R.id.imgDetObraFoto);
        TextView txtDetObraDescripcion = view.findViewById(R.id.txtDetObraDescripcion);

        obrasModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);

        // Observar el cuadro seleccionado y actualizar la UI
        obrasModel.getObraSeleccionada().observe(getViewLifecycleOwner(), obra -> {
            if (obra != null) {
                txtDetObraTitulo.setText(obra.getTitulo());
                Glide.with(getContext())
                        .load(obra.getUrlImagen())
                        .centerCrop()
                        .into(imgDetObraFoto);
                txtDetObraDescripcion.setText(obra.getDescripcion());
            }
        });

        ImageView btnBack = view.findViewById(R.id.icono_atras);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(obrasModel != null){
                    obrasModel.setClosePictureById(1);
                }
            }
        });
        return view;
    }
}