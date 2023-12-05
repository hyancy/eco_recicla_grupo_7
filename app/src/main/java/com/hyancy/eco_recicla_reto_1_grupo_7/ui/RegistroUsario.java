package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.ui.models.UserModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;

import java.util.ArrayList;

public class RegistroUsario extends AppCompatActivity {
    FloatingActionButton btnCerrar;
    CheckBox cbxTerminosCondiciones, cbxPoliticasPrivacidad;
    Button btnRegistrarUsuario;
    ProgressBar progressBar;
    TextView tvYaTieneCuenta;
    EditText edtName, edtAge, edtEmail, edtConfirmEmail, edtPassword, edtConfirmPassword;
    FirebaseAuth mAuth;
    UserViewModel userViewModel;
    UserModel userModel;

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

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

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
        edtAge = findViewById(R.id.age_user_register);
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
        createUser();
        tvYaTieneCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(initIntents().get(2));
                finish();
            }
        });
    }

    public void createUser() {
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(v.VISIBLE);

                String name = edtName.getText().toString().trim();
                Integer age = Integer.parseInt(edtAge.getText().toString().trim());
                String email = edtEmail.getText().toString().trim();
                String confirmUser = edtConfirmEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmPassword = edtConfirmPassword.getText().toString().trim();

                userModel = new UserModel(name, age, email, password);

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Ingrese un nombre", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Ingrese un email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmUser)) {
                    Toast.makeText(getApplicationContext(), "Confirme email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!email.equals(confirmUser)) {
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
                if (!email.equals(confirmUser) || !password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Los datos no coinciden", Toast.LENGTH_LONG).show();
                    return;
                }
                userViewModel.createUser(userModel.getName(), userModel.getAge(), userModel.getEmail(), userModel.getPassword());
                startActivity(initIntents().get(2));
/*
                mAuth.createUserWithEmailAndPassword(userModel.getEmail(), userModel.getPassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(v.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in userModel's information
                                    Toast.makeText(getApplicationContext(), "Cuenta creada con exito!.",
                                            Toast.LENGTH_SHORT).show();
                                    clearComponents();
                                    FirebaseAuth.getInstance().signOut();
                                    onDestroy();
                                    Intent intentLogin = new Intent(RegistroUsario.this, Login.class);
                                    startActivity(intentLogin);
                                } else {
                                    // If sign in fails, display a message to the userModel.
                                    Toast.makeText(getApplicationContext(), "Cuenta ya existe!!!.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/
            }
        });
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentHome = new Intent(RegistroUsario.this, Index.class);
        Intent intentTerminosCondiciones = new Intent(RegistroUsario.this, PoliticaPrivacidadTerminos.class);
        Intent intentLogin = new Intent(RegistroUsario.this, Login.class);

        listaIntents.add(intentHome);
        listaIntents.add(intentTerminosCondiciones);
        listaIntents.add(intentLogin);

        return listaIntents;
    }


    private void clearComponents() {
        edtName.setText("");
        edtAge.setText("");
        edtEmail.setText("");
        edtConfirmEmail.setText("");
        edtPassword.setText("");
        edtConfirmPassword.setText("");
    }
}