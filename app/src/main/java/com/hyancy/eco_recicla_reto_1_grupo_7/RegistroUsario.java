package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import kotlin.text.RegexOption;

public class RegistroUsario extends AppCompatActivity {
    FloatingActionButton btnCerrar;
    CheckBox cbxTerminosCondiciones, cbxPoliticasPrivacidad;
    Button btnRegistrarUsuario;
    ProgressBar progressBar;
    TextView tvYaTieneCuenta;
    EditText edtName, edtEmail, edtConfirmEmail, edtPassword, edtConfirmPassword;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usario);
        mAuth = FirebaseAuth.getInstance();

        initComponents();
        listenersButtons();

    }


    private void initComponents() {
        btnCerrar = findViewById(R.id.btn_cerrar);
        cbxTerminosCondiciones = findViewById(R.id.checkbox_terminos_condiciones);
        cbxPoliticasPrivacidad = findViewById(R.id.checkbox_privacidad);
        btnRegistrarUsuario = findViewById(R.id.btn_registar_usuario);
        tvYaTieneCuenta = findViewById(R.id.tv_ya_tiene_cuenta);

        edtName = findViewById(R.id.name_user_register);
        edtEmail = findViewById(R.id.email_user_register);
        edtConfirmEmail = findViewById(R.id.confirm_email_user_register);
        edtPassword = findViewById(R.id.password_user_register);
        edtConfirmPassword = findViewById(R.id.confirm_password_user_register);

        progressBar = findViewById(R.id.progress_bar);
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

                //startActivity(initIntents().get(1));
            }
        });
        cbxPoliticasPrivacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(initIntents().get(1));
            }
        });
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(v.VISIBLE);
                String name, user, confirmUser, password, confirmPassword;
                name = edtName.getText().toString();
                user = edtEmail.getText().toString();
                confirmUser = edtConfirmEmail.getText().toString();
                password = edtPassword.getText().toString();
                confirmPassword = edtConfirmPassword.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Ingrese un nombre", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(getApplicationContext(), "Ingrese un email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmUser)) {
                    Toast.makeText(getApplicationContext(), "Confirme email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!user.equals(confirmUser)) {
                    Toast.makeText(getApplicationContext(), "Emails no coinciden", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Ingrese una contraseña", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Confirme contraseña", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!user.equals(confirmUser) || !password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Los datos no coinciden", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(user, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(v.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getApplicationContext(), "Cuenta creada con exito!.",
                                            Toast.LENGTH_SHORT).show();
                                    clearComponents();
                                    FirebaseAuth.getInstance().signOut();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(), "No se pudo crear la cuenta!.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
        tvYaTieneCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(initIntents().get(2));
                finish();
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

    private void clearComponents() {
        edtName.setText("");
        edtEmail.setText("");
        edtConfirmEmail.setText("");
        edtPassword.setText("");
        edtConfirmPassword.setText("");
    }
}