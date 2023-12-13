package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
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
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private Button btnLogin;
    private TextInputEditText userLogin, passwordLogin;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private TextView tvNoHaveCount, tvForgottenPassword;
    private UserViewModel userViewModel;


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
        setContentView(R.layout.activity_login);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        mAuth = FirebaseAuth.getInstance();

        initComponents();
        listenersButtons();
    }

    private void initComponents() {
        btnLogin = findViewById(R.id.btn_iniciar_sesion);
        userLogin = findViewById(R.id.user_login);
        passwordLogin = findViewById(R.id.password_login);
        progressBar = findViewById(R.id.progress_bar);

        tvNoHaveCount = findViewById(R.id.tv_no_tiene_cuenta);
        tvForgottenPassword = findViewById(R.id.tv_olvido_contraseña);
    }

    private void listenersButtons() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, password;
                user = userLogin.getText().toString();
                password = passwordLogin.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(getApplicationContext(), "Ingrese su usuario", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(v.GONE);
                    showDialogCompleteDataLogin();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Ingrese su contraseña", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(v.GONE);
                    showDialogCompleteDataLogin();
                    return;
                }
                progressBar.setVisibility(v.VISIBLE);
                mAuth.signInWithEmailAndPassword(user, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(v.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in userModel's information
                                    Toast.makeText(getApplicationContext(), "Login exitoso!",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(initIntents().get(0));
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the userModel.
                                    Toast.makeText(getApplicationContext(), "Login fallido, compruebe sus datos de ingreso!.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        tvNoHaveCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
                finish();
            }
        });

        tvForgottenPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFogottenPassword();
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

    private void showDialogFogottenPassword() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_forgotten_password, null);

        dialogBuilder.setView(view);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        TextInputEditText edtUser = view.findViewById(R.id.edt_user);
        //TextInputEditText edtEmail = view.findViewById(R.id.edt_email);
        Button btnAceptSendPassword = view.findViewById(R.id.btn_acept);
        Button btnCancelSendPassword = view.findViewById(R.id.btn_cancel);

        btnAceptSendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                //String email = edtEmail.getText().toString();
                System.out.println("EL USUARIO ES " + user);
                if (user.isEmpty() /*|| email.isEmpty()*/) {
                    Toast.makeText(getApplicationContext(), "Completa todos los datos!!!", Toast.LENGTH_LONG).show();
                } else if (!user.toString().equals("usuario en FireBase") /*&& !email.equals("correo en FireBase")*/) {
                    Toast.makeText(getApplicationContext(), "Usuario no registrado!!!", Toast.LENGTH_LONG).show();
                } else {
                    // TODO ENVIAR CORREO PARA REINICIAR CONTRASEÑA
                    Toast.makeText(view.getContext(), "Revise su correo electrónico", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        btnCancelSendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void showDialogCompleteDataLogin() {
        AlertDialog.Builder dialogCompleteDataLogin = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_complete_data_login, null);
        dialogCompleteDataLogin.setView(view);
        final AlertDialog dialog = dialogCompleteDataLogin.create();
        dialog.show();

        TextView tvCerrar = view.findViewById(R.id.Cerrar);

        tvCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Se cerro dialogo", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }
}
