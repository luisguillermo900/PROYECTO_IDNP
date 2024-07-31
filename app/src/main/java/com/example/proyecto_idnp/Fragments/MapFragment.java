package com.example.proyecto_idnp.Fragments;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.proyecto_idnp.Customviews.SalaView;
import com.example.proyecto_idnp.Entidades.ResultadoFiltro;
import com.example.proyecto_idnp.Modelos.CanvasViewModel;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.Customviews.MapView;
import com.example.proyecto_idnp.R;
//import com.example.proyecto_idnp.Modelos.CuadrosViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ObrasViewModel itemViewModel;
    private CanvasViewModel canvasViewModel;
    private MapView mapView;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ScrollView scrollView = new ScrollView(requireContext());
        FrameLayout frameLayout = new FrameLayout(requireContext());
        mapView = new MapView(requireContext());
        frameLayout.addView(mapView);
        scrollView.addView(frameLayout);
        return scrollView;
    }

    public void onViewCreated( View view, Bundle savedInstanceStatus) {
        super.onViewCreated(view, savedInstanceStatus);
        //itemViewModel = new ViewModelProvider(requireActivity()).get(ObrasViewModel.class);
        //mapView.setListener(itemViewModel);
        canvasViewModel = new ViewModelProvider(requireActivity()).get(CanvasViewModel.class);
        mapView.setListener2(canvasViewModel);

        canvasViewModel.getSelectRoom().observe(getViewLifecycleOwner(), roomId -> {
            if (roomId != null) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragments, SalaFragment.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
        canvasViewModel.setSelectRoom(null);
    }
}