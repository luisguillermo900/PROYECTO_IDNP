package com.example.proyecto_idnp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import com.example.proyecto_idnp.Fragments.CuadrosFragment;
import com.example.proyecto_idnp.Fragments.HomeFragment;
import com.example.proyecto_idnp.Fragments.MapFragment;
import com.example.proyecto_idnp.Fragments.QrFragment;
import com.example.proyecto_idnp.Modelos.CuadrosViewModel;
import com.example.proyecto_idnp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private TextView layout;
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

        CuadrosViewModel cuadrosModel = new ViewModelProvider(this).get(CuadrosViewModel.class);
        BottomNavigationView menu = findViewById(R.id.menuNavegacion);
        menu.setOnNavigationItemSelectedListener(item -> {

            Fragment fragment;

            if(item.getItemId() == R.id.menu_home){
                layout.setVisibility(View.GONE);
                fragment = new HomeFragment();
            } else if (item.getItemId() == R.id.menu_cuadros){
                layout.setVisibility(View.VISIBLE);
                layout.setText("Cuadros");
                fragment = new CuadrosFragment();
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
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit();
            return true;
        });
    }
}