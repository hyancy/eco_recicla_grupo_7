package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PoliticaPrivacidadTerminos extends AppCompatActivity {
    Button btnAceptoTerminos, btnNoAceptoTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politica_privacidad_terminos);

        initComponents();
        listenersButtons();

    }

    private void initComponents() {
        btnAceptoTerminos = findViewById(R.id.btn_acepto_terminos);
        btnNoAceptoTerminos = findViewById(R.id.btn_no_acepto_terminos);
    }

    private void listenersButtons() {
        btnAceptoTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        btnNoAceptoTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentAceptoTerminos = new Intent(PoliticaPrivacidadTerminos.this, RegistroUsario.class);
        Intent intentNoAceptoTerminos = new Intent(PoliticaPrivacidadTerminos.this, RegistroUsario.class);

        listaIntents.add(intentAceptoTerminos);
        listaIntents.add(intentNoAceptoTerminos);

        return  listaIntents;
    }
}