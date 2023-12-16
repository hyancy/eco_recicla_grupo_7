package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;

public class DesafiosLogros extends AppCompatActivity {
    private Button btnLogros, btnDesafios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafios_logros);
        btnLogros = findViewById(R.id.btn_logros);
        btnDesafios = findViewById(R.id.btn_desafios);

        btnDesafios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DesafiosLogros.this, Desafios.class);
                startActivity(intent);
            }
        });
    }
}