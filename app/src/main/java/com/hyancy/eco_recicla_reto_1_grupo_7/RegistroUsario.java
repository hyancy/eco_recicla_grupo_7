package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import kotlin.text.RegexOption;

public class RegistroUsario extends AppCompatActivity {
    FloatingActionButton btnCerrar;
    CheckBox cbxTerminosCondiciones, cbxPoliticasPrivacidad;
    Button btnRegistrarUsuario;
    TextView tvYaTieneCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usario);

        initComponents();
        listenersButtons();

    }


    private void initComponents() {
        btnCerrar = findViewById(R.id.btn_cerrar);
        cbxTerminosCondiciones = findViewById(R.id.checkbox_terminos_condiciones);
        cbxPoliticasPrivacidad = findViewById(R.id.checkbox_privacidad);
        btnRegistrarUsuario = findViewById(R.id.btn_registar_usuario);
        tvYaTieneCuenta = findViewById(R.id.tv_ya_tiene_cuenta);
    }

    private void listenersButtons() {
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        cbxTerminosCondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
        cbxPoliticasPrivacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(2));
            }
        });
        tvYaTieneCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(2));
            }
        });
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentHome = new Intent(RegistroUsario.this, MainActivity.class);
        Intent intentTerminosCondiciones = new Intent(RegistroUsario.this, PoliticaPrivacidadTerminos.class);
        Intent intentLogin = new Intent(RegistroUsario.this, Login.class);

        listaIntents.add(intentHome);
        listaIntents.add(intentTerminosCondiciones);
        listaIntents.add(intentLogin);

        return listaIntents;
    }
}