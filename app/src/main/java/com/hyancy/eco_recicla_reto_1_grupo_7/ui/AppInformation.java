package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;

public class AppInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_app);
        Button btnRegresar;
        btnRegresar = findViewById(R.id.btn_regresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppInformation.this, Index.class);
                startActivity(intent);
            }
        });

    }
}