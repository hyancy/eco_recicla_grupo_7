package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import kotlin.text.RegexOption;

public class RegistroUsario extends AppCompatActivity {
    FloatingActionButton btnCerrar;
    CheckBox cbxTerminosCondiciones, cbxPoliticasPrivacidad;
    Button btnRegistrarUsuario;
    TextView tvYaTieneCuenta;

    EditText etNombre;
    EditText etEmail;
    EditText etUsuario;
    EditText etContrasena;
    EditText etConfirmarContrasena;

    Button btnAceptarTodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usario);
        btnRegistrarUsuario = findViewById(R.id.btn_registar_usuario);
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNombre = findViewById(R.id.et_nombre);
                etEmail = findViewById(R.id.et_email);
                etUsuario = findViewById(R.id.et_usuario);
                etContrasena = findViewById(R.id.et_contraseña);
                etConfirmarContrasena = findViewById(R.id.et_confirmar_contraseña);
                String etNombreStr = etNombre.getText().toString();
                String etEmailStr = etEmail.getText().toString();
                String etUsuarioStr = etUsuario.getText().toString();
                String etContrasenaStr = etContrasena.getText().toString();
                String etConfirmarStr = etConfirmarContrasena.getText().toString();
                if(etNombreStr.isEmpty() || etEmailStr.isEmpty() || etUsuarioStr.isEmpty() || etContrasenaStr.isEmpty() || etConfirmarStr.isEmpty() ) {
                    // Primer paso para crear un dialogo: asignando valores
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistroUsario.this);
                    View selector = getLayoutInflater().inflate(R.layout.dialog_completa_todo, null);

                    // Segundo paso: pasando al builder
                    builder.setView(selector);

                    // Creando dialogo
                    AlertDialog dialog = builder.create();
                    btnAceptarTodo = findViewById(R.id.btn_acepta_todo);
                    btnAceptarTodo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.hide();
                        }
                    });
                    dialog.show();


                } else {
                    Intent intent = new Intent(RegistroUsario.this, Login.class);
                    startActivity(intent);
                }
            }
        });



    }



}