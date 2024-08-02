package com.example.proyecto_idnp.Fragments;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
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
import com.example.proyecto_idnp.Abstract.AppDatabase;
import com.example.proyecto_idnp.Dao.DaoAutor;
import com.example.proyecto_idnp.Dao.DaoGaleria;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.R;
import com.example.proyecto_idnp.Servicios.ServicioAudio;

import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.google.zxing.BarcodeFormat;

public class DetalleObraFragment extends Fragment {

    private static final String ARG_PARAM1 = "estaReproduciendo";

    private String mParam1;
    private AppDatabase appBD;
    private DaoGaleria daoGaleria;
    private DaoAutor daoAutor;
    private ImageButton btnReproducirPausar;
    private ImageButton btnDetener;
    private ImageButton btnReiniciar;
    private boolean estaReproduciendo = false;
    private ObrasViewModel obrasModel;
    private String nombreObra = "";
    private String nombreArchivo = "";

    //Implementar en vista QR
    //private ImageView imgQr;

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

    public static DetalleObraFragment newInstance(boolean estaReproduciendo) {
        DetalleObraFragment fragment = new DetalleObraFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, estaReproduciendo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = String.valueOf(getArguments().getBoolean(ARG_PARAM1));
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_obra, container, false);
        appBD = AppDatabase.getInstance(getContext());
        daoGaleria = appBD.daoGaleria();
        daoAutor = appBD.daoAuthor();
        TextView txtDetObraTitulo = view.findViewById(R.id.txtDetObraTitulo);
        ImageView imgDetObraFoto = view.findViewById(R.id.imgDetObraFoto);
        TextView txtDetObraDescripcion = view.findViewById(R.id.txtDetObraDescripcion);
        ImageView btnObraVolver = view.findViewById(R.id.btnObraVolver);
        TextView txtDetObraFecha = view.findViewById(R.id.txtDetObraFecha);
        TextView txtDetObraAutor = view.findViewById(R.id.txtDetObraAutor);
        TextView txtDetObraGaleria = view.findViewById(R.id.txtDetObraGaleria);

        btnReproducirPausar = view.findViewById(R.id.btnReproducirPausar);
        btnDetener = view.findViewById(R.id.btnDetener);
        btnReiniciar = view.findViewById(R.id.btnReiniciar);
        //Implementar en vista QR
        //imgQr = view.findViewById(R.id.imgQr);
        if (mParam1 != null) {
            estaReproduciendo = Boolean.parseBoolean(mParam1);
            if(estaReproduciendo){
                btnReproducirPausar.setImageResource(R.drawable.baseline_pause_24);
            } else {
                btnReproducirPausar.setImageResource(R.drawable.baseline_play_arrow_24);
            }
        }
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receptorBotonesReproduccion,
                new IntentFilter("ACTUALIZAR_UI"));

        obrasModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);
        Log.d(TAG, "Cargando detalle obra");
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

                controlAudio("ACCION_REPRODUCIR_PAUSAR", nombreArchivo);
            }
        });
        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estaReproduciendo = false;
                btnReproducirPausar.setImageResource(R.drawable.baseline_play_arrow_24);
                controlAudio("ACCION_DETENER", nombreArchivo);
            }
        });
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estaReproduciendo = true;
                btnReproducirPausar.setImageResource(R.drawable.baseline_pause_24);
                controlAudio("ACCION_REINICIAR", nombreArchivo);
            }
        });

        // l: Obtener el ID de la obra desde los argumentos y cargarla
        /*if (getArguments() != null) {
            String idObra = getArguments().getString("param1");
            if (idObra != null) {
                obrasModel.setObraSeleccionada(Integer.parseInt(idObra));
            }
        }*/

        // Observar el cuadro seleccionado y actualizar la UI
        obrasModel.getObraSeleccionada().observe(getViewLifecycleOwner(), obra -> {
            if (obra != null) {
                nombreObra = obra.getTitulo();
                nombreArchivo = obra.getArchivoAudio();
                txtDetObraTitulo.setText(obra.getTitulo());
                Glide.with(getContext())
                        .load(obra.getUrlImagen())
                        .centerCrop()
                        .into(imgDetObraFoto);
                txtDetObraAutor.setText(daoAutor.getAutor(obra.getIdAutor()).nombre);
                txtDetObraGaleria.setText(daoGaleria.getGaleria(obra.getIdGaleria()).nombre);
                txtDetObraFecha.setText(obra.getFecha());
                txtDetObraDescripcion.setText(obra.getDescripcion());
                // l: Generar el código QR con el identificador del cuadro
                //Implementar en vista QR
                //generarCodigoQR(String.valueOf(obra.getId()));
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
        intentServicio.putExtra("nombreObra", nombreObra);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(comando.equals("ACCION_DETENER")){
                requireActivity().startService(intentServicio);
            } else {
                requireActivity().startForegroundService(intentServicio);
            }
        } else {
            requireActivity().startService(intentServicio);
        }
    }
    //funcion para generar QR (Implementar en Vista QR)
    /*private void generarCodigoQR(String idObra) {
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(idObra, BarcodeFormat.QR_CODE, 750, 750);
            imgQr.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    //l
    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(receptorBotonesReproduccion);
    }
}