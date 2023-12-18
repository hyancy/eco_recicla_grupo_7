package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;

public class MyProfile extends AppCompatActivity {
    private TextView tvNameUser;
    private Button btnUserDesafio, btnUserLogros;
    private ImageButton btnRegresarMiPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        tvNameUser = findViewById(R.id.tv_nombre_user);
        btnUserDesafio = findViewById(R.id.btn_user_desafios);
        btnUserLogros = findViewById(R.id.btn_user_logros);
        btnRegresarMiPerfil = findViewById(R.id.btn_regresar_perfil);

        btnRegresarMiPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnUserDesafio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfile.this, Challenge.class);
                startActivity(intent);
            }
        });
        btnUserLogros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfile.this, Achievements.class);
                startActivity(intent);
            }
        });
    }
}
