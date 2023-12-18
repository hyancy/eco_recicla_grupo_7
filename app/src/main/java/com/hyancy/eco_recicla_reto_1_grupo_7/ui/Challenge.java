package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;

public class Challenge extends AppCompatActivity {
    Button btnRegresarDesafios, btnDesafio1, btnDesafio2, btnDesafio3, btnDesafio4, btnDesafio5, btnDesafio6, btnDesafio7, btnDesafio8, btnDesafio9, btnDesafio10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        btnDesafio1 = findViewById(R.id.btn_desafio_1);
        btnDesafio2 = findViewById(R.id.btn_desafio_2);
        btnDesafio3 = findViewById(R.id.btn_desafio_3);
        btnDesafio4 = findViewById(R.id.btn_desafio_4);
        btnDesafio5 = findViewById(R.id.btn_desafio_5);
        btnDesafio6 = findViewById(R.id.btn_desafio_6);
        btnDesafio7 = findViewById(R.id.btn_desafio_7);
        btnDesafio8 = findViewById(R.id.btn_desafio_8);
        btnDesafio9 = findViewById(R.id.btn_desafio_9);
        btnDesafio10 = findViewById(R.id.btn_desafio_10);
        btnRegresarDesafios = findViewById(R.id.btn_regresar_desafios);

        btnRegresarDesafios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnDesafio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDesafio();
            }
        });
        btnDesafio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDesafio();
            }
        });
        btnDesafio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDesafio();
            }
        });
        btnDesafio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDesafio();
            }
        });
        btnDesafio5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDesafio();
            }
        });
        btnDesafio6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDesafio();
            }
        });
        btnDesafio7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDesafio();
            }
        });
        btnDesafio8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDesafio();
            }
        });
        btnDesafio9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDesafio();
            }
        });
        btnDesafio10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDesafio();
            }
        });
    }

    private void showDialogDesafio() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_challenge, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();
        TextView tvCerrarDesafio = view.findViewById(R.id.btn_cerrar_desafio);

        tvCerrarDesafio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}