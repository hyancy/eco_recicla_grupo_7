package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hyancy.eco_recicla_reto_1_grupo_7.R;

import java.util.ArrayList;

public class Index extends AppCompatActivity {
    private ImageButton consejos, puntosLimpios, desafiosLogros, infoApp;
    private Button btnIniciarSesion, btnRegistrarse;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        try {
            Thread.sleep(2000);
            setTheme(R.style.Theme_Eco_recicla_reto_1_grupo_7);
        } catch (InterruptedException e) {
            Toast.makeText(this, "No es posible iniciar la App!!!", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }

        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intentPrincipal = new Intent(getApplicationContext(), Principal.class);
            startActivity(intentPrincipal);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(2000);
            setTheme(R.style.Theme_Eco_recicla_reto_1_grupo_7);
        } catch (InterruptedException e) {
            Toast.makeText(this, "No es posible iniciar la App!!!", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mAuth = FirebaseAuth.getInstance();

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
                Intent intent = new Intent(Index.this, DesafiosLogros.class);
                startActivity(intent);
            }
        });
        infoApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Index.this, InformacionApp.class);
                startActivity(intent);
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
        Intent intenIniciarSesion = new Intent(Index.this, Login.class);
        Intent intentRegistrarse = new Intent(Index.this, RegistroUsario.class);
        Intent intentConsejos = new Intent(Index.this, Consejos.class);
        Intent intentPuntosLimpios = new Intent(Index.this, PuntoLimpio.class);

        listaIntents.add(intenIniciarSesion);
        listaIntents.add(intentRegistrarse);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentPuntosLimpios);

        return listaIntents;
    }

}