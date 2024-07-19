package com.example.proyecto_idnp.Actividades;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

//import com.example.proyecto_idnp.Adaptadores.Cuadro;
//import com.example.proyecto_idnp.Fragments.CuadrosFragment;
//import com.example.proyecto_idnp.Fragments.DetalleCuadroFragment;

import com.example.proyecto_idnp.Fragments.ExplorarFragment;
import com.example.proyecto_idnp.Fragments.DetalleObraFragment;

import com.example.proyecto_idnp.Fragments.HomeFragment;
import com.example.proyecto_idnp.Fragments.MapFragment;
import com.example.proyecto_idnp.Fragments.QrFragment;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private TextView layout;
    private ObrasViewModel itemViewModel;
    private DetalleObraFragment obraFragment;
    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layout = findViewById(R.id.pageHomeActivity);
        layout.setVisibility(View.GONE);

//        BottomNavigationView menu = findViewById(R.id.menuNavegacion);
//        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                fragmentManager = getSupportFragmentManager();
//                if(menuItem.getItemId() == R.id.menu_home){
//                    layout.setVisibility(View.GONE);
//                    loadFragment(new HomeFragment());
//                    return true;
//                } else if (menuItem.getItemId() == R.id.menu_cuadros) {
//                    layout.setVisibility(View.VISIBLE);
//                    layout.setText("Cuadros");
//                    loadFragment(new CuadrosFragment());
//                    return true;
//                } else if (menuItem.getItemId() == R.id.menu_mapa) {
//                    layout.setVisibility(View.VISIBLE);
//                    layout.setText("Mapa");
//                    loadFragment(new MapFragment());
//                    return true;
//                } else if (menuItem.getItemId() == R.id.menu_qr) {
//                    layout.setVisibility(View.VISIBLE);
//                    layout.setText("Qr");
//                    loadFragment(new QrFragment());
//                    return true;
//                } else {
//                    return false;
//                }

        ObrasViewModel cuadrosModel = new ViewModelProvider(this).get(ObrasViewModel.class);
        BottomNavigationView menu = findViewById(R.id.menuNavegacion);
        menu.setOnNavigationItemSelectedListener(item -> {

            Fragment fragment;

            if(item.getItemId() == R.id.menu_home){
                layout.setVisibility(View.GONE);
                fragment = new HomeFragment();
            } else if (item.getItemId() == R.id.menu_obras){
                layout.setVisibility(View.VISIBLE);
                layout.setText("Obras");
                fragment = new ExplorarFragment();
            } else if (item.getItemId() == R.id.menu_mapa){
                layout.setVisibility(View.VISIBLE);
                layout.setText("Mapa");
                fragment = new MapFragment();
            } else if (item.getItemId() == R.id.menu_qr) {
                layout.setVisibility(View.VISIBLE);
                layout.setText("Qr");
                fragment = new QrFragment();
            } else {
                return false;
            }

//        itemViewModel = new ViewModelProvider(this).get(CuadrosViewModel.class);
////        itemViewModel.getSelectPicture().observe(this, id -> {
////            itemViewModel.setCuadroSeleccionadoPorId(1);
////            pictureFragment = new DetalleCuadroFragment();
////            loadFragment(pictureFragment);
////        });
//        itemViewModel.getCuadroSeleccionado().observe(this, cuadro -> {
//            if (cuadro != null) {
//                pictureFragment = new DetalleCuadroFragment();
//                loadFragment(pictureFragment);
//                Log.d("HomeActivity","pictureFragment");
//            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit();


            itemViewModel = new ViewModelProvider(this).get(ObrasViewModel.class);
            itemViewModel.getObraSeleccionada().observe(this, id -> {
                obraFragment = DetalleObraFragment.newInstance("","");
                loadFragment(obraFragment);
            });
            return true;
        });
    }

    private void loadFragment(Fragment fragment){
        if (fragmentManager != null){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
            fragmentTransaction.commit();
        }
    }
}