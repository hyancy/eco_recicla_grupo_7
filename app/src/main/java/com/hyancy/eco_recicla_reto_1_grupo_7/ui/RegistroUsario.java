package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
                // startActivity(initIntents().get(0));
            }
        });

        cbxTerminosCondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbxTerminosCondiciones.isChecked()) {
                    showDialogTerminosYCondicones();
                } else {
                    cbxTerminosCondiciones.isChecked();
                }
            }
        });
        cbxPoliticasPrivacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbxPoliticasPrivacidad.isChecked()) {
                    showDialogPrivacidad();
                } else {
                    cbxPoliticasPrivacidad.isChecked();
                }
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
                String email = edtEmail.getText().toString().trim();
                String age = edtAge.getText().toString().trim();
                String confirmEmail = edtConfirmEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmPassword = edtConfirmPassword.getText().toString().trim();


                if (name.isEmpty() || age.isEmpty() || email.isEmpty() || confirmEmail.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    showDialogCompleteAll();
                } else if (!(password.equals(confirmPassword))) {
                    showDialogContrasenaNoCoincide();
                } else if (!(email.equals(confirmEmail))) {
                    showDialogContrasenaNoCoincide();
                } else if (!(cbxTerminosCondiciones.isChecked())) {
                    showDialogAceptaCondicionesYTerminos();
                } else if (!(cbxPoliticasPrivacidad.isChecked())) {
                    showDialogAceptaPrivacidad();
                } else {
                    Integer userAge = Integer.parseInt(age);
                    userModel = new UserModel(name, userAge, email, password);
                    showDialogoRegistroCompleto();
                    userViewModel.createUser(userModel.getName(), userModel.getAge(), userModel.getEmail(), userModel.getPassword());

                }
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


    private void showDialogCompleteAll() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setView(R.layout.dialog_completa_todo).create().show();

    }

    private void showDialogoRegistroCompleto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearComponents();
                authenticateUser();
            }
        });
        builder.setView(R.layout.dialog_registro_completo).create().show();
    }

    private void authenticateUser() {
                            mAuth.createUserWithEmailAndPassword(userModel.getEmail(), userModel.getPassword())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in userModel's information
                                        Toast.makeText(getApplicationContext(), "Cuenta creada con exito!.",
                                                Toast.LENGTH_SHORT).show();
                                        clearComponents();
                                        FirebaseAuth.getInstance().signOut();
                                        onDestroy();
                                        startActivity(initIntents().get(2));
                                    } else {
                                        // If sign in fails, display a message to the userModel.
                                        Toast.makeText(getApplicationContext(), "Cuenta ya existe!!!.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
    }

    private void showDialogContrasenaNoCoincide() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setView(R.layout.dialog_contrasena_no_coincide).create().show();

    }

    private void showDialogAceptaCondicionesYTerminos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton("CERRAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setView(R.layout.dialog_acepta_terminos_condiciones).create().show();
    }

    private void showDialogTerminosYCondicones() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cbxTerminosCondiciones.isChecked();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("NO ACEPTO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cbxTerminosCondiciones.setChecked(false);
                dialog.cancel();
            }
        });
        builder.setView(R.layout.dialog_terminos_condiciones).create().show();
    }

    private void showDialogAceptaPrivacidad() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setView(R.layout.dialog_acepta_privacidad).create().show();
    }

    private void showDialogPrivacidad() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("SI ACEPTO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cbxPoliticasPrivacidad.isChecked();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("NO ACEPTO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cbxPoliticasPrivacidad.setChecked(false);
                dialog.cancel();
            }
        });
        builder.setView(R.layout.dialog_privacidad).create().show();
    }
}
