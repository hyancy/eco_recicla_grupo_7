package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;

import java.util.ArrayList;

public class Statistic extends AppCompatActivity {
    private ImageView categoriasBottomBar, estadisticasBottomBar, consejosBottomBar, homeAppBottomBar, logoutBottomBar;
    private ImageView imageViewToolbar;
    private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        setToolbar();
        initComponents();
        listenersMenuAppBar();
    }

    private void initComponents() {
        homeAppBottomBar = findViewById(R.id.home_menu_bottom_bar);
        categoriasBottomBar = findViewById(R.id.categorias_menu_bottom_bar);
        estadisticasBottomBar = findViewById(R.id.estadisticas_menu_bottom_bar);
        consejosBottomBar = findViewById(R.id.consejos_menu_bottom_bar);
        logoutBottomBar = findViewById(R.id.logout_menu_bottom_bar);
        imageViewToolbar = findViewById(R.id.menu_hamburguesa);
    }

    private void listenersMenuAppBar() {
        homeAppBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(4));
            }
        });
        categoriasBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        estadisticasBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        consejosBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(2));
            }
        });
        logoutBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutCurrentSesion();
            }
        });

        imageViewToolbar.setOnClickListener(v -> {
            Toast.makeText(this, "Menu", Toast.LENGTH_LONG).show();
        });
    }


    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentCategorias = new Intent(Statistic.this, Categoria.class);
        Intent intentEstadisticas = new Intent(Statistic.this, Statistic.class);
        Intent intentConsejos = new Intent(Statistic.this, Consejos.class);
        Intent intentLogout = new Intent(Statistic.this, Index.class);
        Intent intentPrincipal = new Intent(Statistic.this, Principal.class);

        listaIntents.add(intentCategorias);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);
        listaIntents.add(intentPrincipal);

        return listaIntents;
    }

    private void logoutCurrentSesion() {
        userViewModel.logoutSesion();
        Intent intentLogout = new Intent(getApplicationContext(), Index.class);
        startActivity(intentLogout);
        finish();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_foreground);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lateral, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_inicio) {
            startActivity(initIntents().get(4));
        } else if (item.getItemId() == R.id.menu_categorias) {
            startActivity(initIntents().get(0));
        } else if (item.getItemId() == R.id.menu_estadisticas) {
            item.collapseActionView();
        } else if (item.getItemId() == R.id.menu_consejos) {
            startActivity(initIntents().get(2));
        } else if (item.getItemId() == R.id.menu_info_app) {
            Toast.makeText(this, "Información de la App", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.menu_cerrar_sesion) {
            logoutCurrentSesion();
        }

        return super.onOptionsItemSelected(item);
    }
}