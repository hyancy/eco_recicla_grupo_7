package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    Button iniciarSesion;
    TextView tvNoTieneCuentaRegistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
        listenersButtons();
    }

    private void initComponents() {
        iniciarSesion = findViewById(R.id.btn_iniciar_sesion);
        tvNoTieneCuentaRegistrate = findViewById(R.id.tv_no_tiene_cuenta);
    }

    private void listenersButtons() {
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        tvNoTieneCuentaRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentPrincipal = new Intent(Login.this, Principal.class);
        Intent intentRegistroUsuario = new Intent(Login.this, RegistroUsario.class);

        listaIntents.add(intentPrincipal);
        listaIntents.add(intentRegistroUsuario);

        return listaIntents;
    }
}