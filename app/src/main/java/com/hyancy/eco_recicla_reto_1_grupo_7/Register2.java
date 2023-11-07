package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class Register2 extends AppCompatActivity {
    TextInputEditText edtUser, edtPassword;
    Button btnRegister;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    TextView loginNow;

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
        setContentView(R.layout.activity_register2);
        mAuth = FirebaseAuth.getInstance();

        initComponents();
        listeners();
    }

    private void listeners() {
        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intenLogin = new Intent(getApplicationContext(), Login2.class);
                startActivity(intenLogin);
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(v.VISIBLE);
                String user, password;
                user = edtUser.getText().toString();
                password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(Register2.this, "Ingrese un email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register2.this, "Ingrese una contraseña", Toast.LENGTH_LONG).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(user, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(v.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Register2.this, "Cuenta creada con exito!.",
                                            Toast.LENGTH_SHORT).show();
                                    cleanComponents();
                                    FirebaseAuth.getInstance().signOut();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register2.this, "No se pudo crear la cuenta!.",
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
        btnRegister = findViewById(R.id.btn_register);

        progressBar = findViewById(R.id.progress_bar);
        loginNow = findViewById(R.id.login_now);
    }

    private void cleanComponents(){
        edtUser.setText("");
        edtPassword.setText("");
    }
}