package com.example.proyecto_idnp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.proyecto_idnp.Customviews.MapView;
import com.example.proyecto_idnp.Customviews.SalaView;
import com.example.proyecto_idnp.Entidades.RoomTestEntity;
import com.example.proyecto_idnp.Modelos.CanvasViewModel;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SalaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SalaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CanvasViewModel canvasViewModel;
    private ObrasViewModel obrasViewModel;
    private SalaView salaView;
    private ImageView back;
    private int roomId;

    public SalaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SalaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SalaFragment newInstance(String param1, String param2) {
        SalaFragment fragment = new SalaFragment();
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
        View view = inflater.inflate(R.layout.fragment_sala, container, false);
        salaView = view.findViewById(R.id.salaView);
        SalaView room = new SalaView(requireContext());

        back = view.findViewById(R.id.btnExpoVolverSala);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0){
                    fragmentManager.popBackStack();
                }
            }
        });
        return view;
    }
    public void onViewCreated( View view, Bundle savedInstanceStatus) {
        super.onViewCreated(view, savedInstanceStatus);
        obrasViewModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);
        salaView.setListener2(obrasViewModel);
        obrasViewModel.getObraSeleccionada().observe(getViewLifecycleOwner(), roomId -> {
            if (roomId != null) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragments, DetalleObraFragment.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
        obrasViewModel.setObraSeleccionada(null);
    }
}