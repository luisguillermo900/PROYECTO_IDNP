package com.example.proyecto_idnp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyecto_idnp.Modelos.CuadrosViewModel;
import com.example.proyecto_idnp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleCuadroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleCuadroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CuadrosViewModel cuadrosModel;

    public DetalleCuadroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleCuadroFragment newInstance(String param1, String param2) {
        DetalleCuadroFragment fragment = new DetalleCuadroFragment();
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
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        TextView txtDetalleTitulo = view.findViewById(R.id.txtDetalleTitulo);
        ImageView imgDetalleFoto = view.findViewById(R.id.imgDetalleFoto);
        TextView txtDetalleDescripcion = view.findViewById(R.id.txtDetalleDescripcion);

        cuadrosModel = new ViewModelProvider(requireActivity()).get(CuadrosViewModel.class);

        // Observar el cuadro seleccionado y actualizar la UI
        cuadrosModel.getCuadroSeleccionado().observe(getViewLifecycleOwner(), cuadro -> {
            if (cuadro != null) {
                txtDetalleTitulo.setText(cuadro.getNombreCuadro());
                Glide.with(getContext())
                        .load(cuadro.getFotoCuadro())
                        .centerCrop()
                        .into(imgDetalleFoto);
                txtDetalleDescripcion.setText(cuadro.getDescripcionCuadro());
            }
        });
        return view;
    }
}