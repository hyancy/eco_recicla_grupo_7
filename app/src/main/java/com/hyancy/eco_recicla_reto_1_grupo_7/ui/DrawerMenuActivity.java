package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.hyancy.eco_recicla_reto_1_grupo_7.R;

public class DrawerMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_menu);

        initGUI();
        setToolbar("Mi Menu");
    }

    private void initGUI() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.menu_navigation);

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar(String title) {
        Toolbar toolbar = findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        if (item.getItemId() == R.id.menu_inicio) {
            Toast.makeText(this, "Inicio", Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.menu_categorias) {
            Toast.makeText(this, "Categorias", Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.menu_estadisticas) {
            Toast.makeText(this, "Estadisticas", Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.menu_consejos) {
            Toast.makeText(this, "Consejos", Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.menu_info_app) {
            Toast.makeText(this, "Información de la App", Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.menu_cerrar_sesion) {
            Toast.makeText(this, "Saliendo de la App", Toast.LENGTH_LONG).show();
        }

        return false;
    }
}