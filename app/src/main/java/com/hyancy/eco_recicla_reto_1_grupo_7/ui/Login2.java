package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hyancy.eco_recicla_reto_1_grupo_7.R;

public class Login2 extends AppCompatActivity {
    TextInputEditText edtUser, edtPassword;
    Button btnLogin;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    TextView registerNow;

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
        setContentView(R.layout.activity_login2);
        mAuth = FirebaseAuth.getInstance();

        initComponents();
        listeners();
    }

    private void listeners() {
        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenRegister = new Intent(getApplicationContext(), Register2.class);
                startActivity(intenRegister);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(v.VISIBLE);
                String user, password;
                user = edtUser.getText().toString();
                password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(Login2.this, "Ingrese un email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login2.this, "Ingrese una contraseña", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(user, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(v.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in userModel's information
                                    Toast.makeText(getApplicationContext(), "Login exitoso!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intentPrincipal = new Intent(getApplicationContext(), Principal.class);
                                    startActivity(intentPrincipal);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the userModel.
                                    Toast.makeText(Login2.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    private void initComponents() {
        edtUser = findViewById(R.id.user);
        edtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);

        progressBar = findViewById(R.id.progress_bar);

        registerNow = findViewById(R.id.register_now);
    }
}