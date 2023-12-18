package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;

public class Achievements extends AppCompatActivity {
    private Button btnRegresarLogros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        btnRegresarLogros = findViewById(R.id.btn_regresar_logros);

        btnRegresarLogros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}