package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;

import java.util.ArrayList;

public class Consejos extends AppCompatActivity {
    ImageView categoriasBottomBar, estadisticasBottomBar, consejosBottomBar, homeAppBottomBar, logoutBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        initComponents();
        listenersMenuAppBar();

    }

    private void initComponents() {
        homeAppBottomBar = findViewById(R.id.home_menu_bottom_bar);
        categoriasBottomBar = findViewById(R.id.categorias_menu_bottom_bar);
        estadisticasBottomBar = findViewById(R.id.estadisticas_menu_bottom_bar);
        consejosBottomBar = findViewById(R.id.consejos_menu_bottom_bar);
        logoutBottomBar = findViewById(R.id.logout_menu_bottom_bar);
    }

    private void listenersMenuAppBar() {
        homeAppBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        categoriasBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(initIntents().get(1));
            }
        });
        estadisticasBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(initIntents().get(2));
            }
        });
        consejosBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        logoutBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(3));
            }
        });
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentPrincipal = new Intent(Consejos.this, Principal.class);
        Intent intentCategorias = new Intent(Consejos.this, Categoria.class);
        Intent intentEstadisticas = new Intent(Consejos.this, Statistic.class);
        Intent intentLogout = new Intent(Consejos.this, Index.class);

        listaIntents.add(intentPrincipal);
        listaIntents.add(intentCategorias);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentLogout);

        return listaIntents;
    }
}