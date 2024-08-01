package com.example.proyecto_idnp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrFragment extends Fragment {

    private Button btnScan;
    private EditText txtResultado;

    //l
    private ObrasViewModel obrasViewModel;

    public QrFragment() {
        // Required empty public constructor
    }

    public static QrFragment newInstance(String param1, String param2) {
        QrFragment fragment = new QrFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString("param1");
            String mParam2 = getArguments().getString("param2");
        }
        //l
        obrasViewModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr, container, false);

        btnScan = view.findViewById(R.id.btnScan);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = IntentIntegrator.forSupportFragment(QrFragment.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Escanea un código QR");
                integrator.setCameraId(0);  // 0 para la cámara trasera
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.setOrientationLocked(true);  // Bloquea la orientación a la actual
                integrator.initiateScan();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getActivity(), "Escaneo cancelado", Toast.LENGTH_LONG).show();
            } else {
                String idObra = result.getContents();
                txtResultado.setText(idObra);
                Toast.makeText(getActivity(), "Código escaneado: " + idObra, Toast.LENGTH_LONG).show();
                // Navegar al fragmento de detalles del cuadro
                DetalleObraFragment detalleObraFragment = DetalleObraFragment.newInstance(idObra, null);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedorFragments, detalleObraFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
