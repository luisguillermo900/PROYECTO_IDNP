package com.example.proyecto_idnp.Fragments;

import static android.content.ContentValues.TAG;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
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

    private ImageButton btnReproducirPausar;
    private ImageButton btnDetener;
    private ImageButton btnReiniciar;
    private boolean estaReproduciendo = false;
    private ObrasViewModel obrasModel;

    private BroadcastReceiver receptorBotonesReproduccion = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean reproduciendo = intent.getBooleanExtra("estaReproduciendo",false);
            estaReproduciendo = reproduciendo;
            if (reproduciendo){
                Log.d(TAG, "MainActivity BroadcastReceiver Reproduciendo: " + reproduciendo);
                btnReproducirPausar.setImageResource(R.drawable.baseline_pause_24);
            } else {
                Log.d(TAG, "MainActivity BroadcastReceiver Reproduciendo: " + reproduciendo);
                btnReproducirPausar.setImageResource(R.drawable.baseline_play_arrow_24);
            }
        }
    };

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
        btnReproducirPausar = view.findViewById(R.id.btnReproducirPausar);
        btnDetener = view.findViewById(R.id.btnDetener);
        btnReiniciar = view.findViewById(R.id.btnReiniciar);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receptorBotonesReproduccion,
                new IntentFilter("ACTUALIZAR_UI"));

        obrasModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);

        btnReproducirPausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //estaReproduciendo -> false / estaReproduciendo ->true
                if(estaReproduciendo){
                    estaReproduciendo = false;
                } else {
                    estaReproduciendo = true;
                }
                if(estaReproduciendo){
                    btnReproducirPausar.setImageResource(R.drawable.baseline_pause_24);
                } else {
                    btnReproducirPausar.setImageResource(R.drawable.baseline_play_arrow_24);
                }

                controlAudio("ACCION_REPRODUCIR_PAUSAR", "audio_cuadro.mp3");
            }
        });
        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estaReproduciendo = false;
                btnReproducirPausar.setImageResource(R.drawable.baseline_play_arrow_24);
                controlAudio("ACCION_DETENER", "audio_cuadro.mp3");
            }
        });
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estaReproduciendo = true;
                btnReproducirPausar.setImageResource(R.drawable.baseline_pause_24);
                controlAudio("ACCION_REINICIAR", "audio_cuadro.mp3");
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
    private void controlAudio(String comando, String nombreArchivo) {
        Intent intentServicio = new Intent(getContext(), ServicioAudio.class);
        intentServicio.setAction(comando);
        intentServicio.putExtra("nombreArchivo", nombreArchivo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireActivity().startForegroundService(intentServicio);
        } else {
            requireActivity().startService(intentServicio);
        }
    }
}