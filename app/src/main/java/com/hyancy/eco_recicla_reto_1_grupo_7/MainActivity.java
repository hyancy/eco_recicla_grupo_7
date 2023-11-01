package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton consejos, puntosLimpios, desafiosLogros, infoApp;
    Button btnIniciarSesion, btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        listenersButtons();

    }

    private void initComponents() {
        consejos = findViewById(R.id.icon_tips_home);
        puntosLimpios = findViewById(R.id.icon_clean_points_home);
        desafiosLogros = findViewById(R.id.icon_challenges_achievements_home);
        infoApp = findViewById(R.id.icon_info_home);

        btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion_home);
        btnRegistrarse = findViewById(R.id.btn_registrarse_home);
    }

    private void listenersButtons() {
        consejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(2));
            }
        });
        puntosLimpios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(3));
            }
        });
        desafiosLogros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        infoApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });

    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intenIniciarSesion = new Intent(MainActivity.this, Login.class);
        Intent intentRegistrarse = new Intent(MainActivity.this, RegistroUsario.class);
        Intent intentConsejos = new Intent(MainActivity.this, Consejos.class);
        Intent intentPuntosLimpios = new Intent(MainActivity.this, PuntoLimpio.class);

        listaIntents.add(intenIniciarSesion);
        listaIntents.add(intentRegistrarse);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentPuntosLimpios);

        return  listaIntents;
    }

}