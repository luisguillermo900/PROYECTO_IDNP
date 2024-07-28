package com.example.proyecto_idnp.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.R;
import com.example.proyecto_idnp.Servicios.ServicioAudio;

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
        View view = inflater.inflate(R.layout.fragment_detalle_obra, container, false);
        TextView txtDetObraTitulo = view.findViewById(R.id.txtDetObraTitulo);
        ImageView imgDetObraFoto = view.findViewById(R.id.imgDetObraFoto);
        TextView txtDetObraDescripcion = view.findViewById(R.id.txtDetObraDescripcion);
        ImageView btnObraVolver = view.findViewById(R.id.btnObraVolver);
        ImageButton btnReproducir = view.findViewById(R.id.btnReproducir);;
        ImageButton btnDetener = view.findViewById(R.id.btnDetener);;
        ImageButton btnPausar = view.findViewById(R.id.btnPausar);;
        ImageButton btnReiniciar = view.findViewById(R.id.btnReiniciar);;

        obrasModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);

        btnReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAudioService();
            }
        });
        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pararReproduccion();
            }
        });
        btnPausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausarReproduccion();
            }
        });
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarReproduccion();
            }
        });

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

        btnObraVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack(); // Navega al fragmento anterior en el back stack
                }
            }
        });

        return view;
    }

    private void startAudioService() {
        Intent serviceIntent = new Intent(getContext(), ServicioAudio.class);
        serviceIntent.putExtra("nombreCuadro","nombre");
        serviceIntent.putExtra("nombreArchivo","audio_cuadro.mp3");
        serviceIntent.putExtra("control","reproducir");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireActivity().startForegroundService(serviceIntent);
        } else {
            requireActivity().startService(serviceIntent);
        }
    }
    private void pararReproduccion(){
        Intent serviceIntent = new Intent(getContext(), ServicioAudio.class);
        serviceIntent.putExtra("nombreCuadro","nombre");
        serviceIntent.putExtra("control","parar");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireActivity().startForegroundService(serviceIntent);
        } else {
            requireActivity().startService(serviceIntent);
        }
    }
    private void pausarReproduccion(){
        Intent serviceIntent = new Intent(getContext(), ServicioAudio.class);
        serviceIntent.putExtra("nombreCuadro","nombre");
        serviceIntent.putExtra("control","pausar");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireActivity().startForegroundService(serviceIntent);
        } else {
            requireActivity().startService(serviceIntent);
        }
    }
    private void reiniciarReproduccion(){
        Intent serviceIntent = new Intent(getContext(), ServicioAudio.class);
        serviceIntent.putExtra("nombreCuadro","nombre");
        serviceIntent.putExtra("control","reiniciar");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireActivity().startForegroundService(serviceIntent);
        } else {
            requireActivity().startService(serviceIntent);
        }
    }
}