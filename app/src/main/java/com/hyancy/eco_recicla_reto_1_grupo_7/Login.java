package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    Button iniciarSesion;
    TextInputEditText userLogin, passwordLogin;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    TextView tvNoTieneCuentaRegistrate;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intentPrincipal = new Intent(getApplicationContext(), Principal.class);
            startActivity(intentPrincipal);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        initComponents();
        listenersButtons();
    }

    private void initComponents() {
        iniciarSesion = findViewById(R.id.btn_iniciar_sesion);
        userLogin = findViewById(R.id.user_login);
        passwordLogin = findViewById(R.id.password_login);
        progressBar = findViewById(R.id.progress_bar);

        tvNoTieneCuentaRegistrate = findViewById(R.id.tv_no_tiene_cuenta);
    }

    private void listenersButtons() {
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(v.VISIBLE);
                String user, password;
                user = userLogin.getText().toString();
                password = passwordLogin.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(getApplicationContext(), "Ingrese su usuario", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Ingrese su contraseña", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(user, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(v.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getApplicationContext(), "Login exitoso!",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(initIntents().get(0));
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(), "Login fallido, compruebe sus datos de ingreso!.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        tvNoTieneCuentaRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
                finish();
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