package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {
    Button btnCategorias, btnEstadistica, btnConsejos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        initComponents();
        listenersButtons();

    }

    private void initComponents() {
        btnCategorias = findViewById(R.id.btn_categorias);
        btnEstadistica = findViewById(R.id.btn_estadistica);
        btnConsejos = findViewById(R.id.btn_consejos);
    }

    private void listenersButtons() {
        btnCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        btnEstadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
        btnConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(2));
            }
        });

    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentCategorias = new Intent(Principal.this, Category.class);
        Intent intentEstadisticas = new Intent(Principal.this, Statistic.class);
        Intent intentConsejos = new Intent(Principal.this, Consejos.class);
        Intent intentLogout = new Intent(Principal.this, MainActivity.class);

        listaIntents.add(intentCategorias);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);

        return listaIntents;
    }
}