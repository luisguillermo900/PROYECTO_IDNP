package com.example.proyecto_idnp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import com.example.proyecto_idnp.Fragments.CuadrosFragment;
import com.example.proyecto_idnp.Fragments.HomeFragment;
import com.example.proyecto_idnp.Fragments.MapaFragment;
import com.example.proyecto_idnp.Modelos.CuadrosViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

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
        CuadrosViewModel cuadrosModel = new ViewModelProvider(this).get(CuadrosViewModel.class);
        BottomNavigationView menu = findViewById(R.id.menuNavegacion);
        menu.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment;
            if(item.getItemId() == R.id.menu_home){
                fragment = new HomeFragment();
            } else if (item.getItemId() == R.id.menu_cuadros){
                fragment = new CuadrosFragment();
            } else if (item.getItemId() == R.id.menu_mapa){
                fragment = new MapaFragment();
            } else {
                return false;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit();
            return true;
        });
    }
}