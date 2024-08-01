package com.example.proyecto_idnp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto_idnp.R;

public class InformacionFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageView btnInfoVolver;
    private ImageView imgFotoInfo;
    private TextView txtTituloInfo;
    private TextView txtUbicacionInfo;
    private TextView txtFechaInfo;
    private TextView txtDescripcionInfo;

    public InformacionFragment() {
        // Required empty public constructor
    }

    public static InformacionFragment newInstance(String param1, String param2) {
        InformacionFragment fragment = new InformacionFragment();
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
        View view = inflater.inflate(R.layout.fragment_informacion, container, false);
        btnInfoVolver = view.findViewById(R.id.btnInfoVolver);
        imgFotoInfo = view.findViewById(R.id.imgFotoInfo);
        txtTituloInfo = view.findViewById(R.id.txtTituloInfo);
        txtUbicacionInfo = view.findViewById(R.id.txtUbicacionInfo);
        txtFechaInfo = view.findViewById(R.id.txtFechaInfo);
        txtDescripcionInfo = view.findViewById(R.id.txtDescripcionInfo);

        txtTituloInfo.setText("Centro cultural de la Unsa");
        txtUbicacionInfo.setText("Calle Santa Catalina 101, Arequipa, Peru");
        txtFechaInfo.setText("lunes\t\t8a.m.–8:15p.m.\n" +
                        "martes\t\t8a.m.–8:15p.m.\n" +
                        "miércoles\t\t8a.m.–8:15p.m.\n" +
                        "jueves\t\t8a.m.–8:15p.m.\n" +
                        "viernes\t\t8a.m.–8:15p.m.\n" +
                        "sábado\t\t9:30a.m.–4:15p.m.\n" +
                        "domingo\t\tCerrado");
        txtDescripcionInfo.setText("El Centro Cultural de la Universidad Nacional de San Agustín de Arequipa, " +
                "es parte de la Oficina Universitaria de Promoción y Desarrollo Cultural.\n" +
                "\n" +
                "Desarrolla sus actividades en la calle Santa Catalina 101 en la Casona que perteneció a " +
                "Don Juan Bautista Arróspide y Beláustegui quien la adquiere y concluye a fin del siglo XVIII, " +
                "construcción iniciada en 1743 por doña María Gregoria Vda. de Benavides y Moscoso " +
                "para ser Palacio Episcopal, aquí vivió el Obispo Don Pedro Chaves de la Rosa Galván" +
                " y Amado durante algún tiempo se llamó “Palacio de la Inmaculada Concepción” en 1851, " +
                "fue afectada y se le llamó “Casa Quemada”.\n" +
                "\n" +
                "En 1898 se transfirió la propiedad a Don Simón Yrriberry y posteriormente al Arzobispado" +
                " de Arequipa.");

        btnInfoVolver.setOnClickListener(new View.OnClickListener() {
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
}