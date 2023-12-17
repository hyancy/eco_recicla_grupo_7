package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;

import java.util.ArrayList;

public class CleanPoints extends AppCompatActivity {
    private ImageView btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punto_limpio);

        initComponents();
        listenersButtons();

    }

    private void initComponents() {
        btnCerrar = findViewById(R.id.btn_cerrar);
    }

    private void listenersButtons() {
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intenHome = new Intent(CleanPoints.this, Index.class);

        listaIntents.add(intenHome);

        return listaIntents;
    }
}