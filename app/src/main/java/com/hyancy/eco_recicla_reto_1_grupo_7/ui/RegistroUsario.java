package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.UserModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.DatasetViewModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegistroUsario extends AppCompatActivity {
    FloatingActionButton btnCerrar;
    CheckBox cbxTerminosCondiciones, cbxPoliticasPrivacidad;
    Button btnRegistrarUsuario;
    ProgressBar progressBar;
    TextView tvYaTieneCuenta;
    EditText edtName, edtAge, edtEmail, edtConfirmEmail, edtPassword, edtConfirmPassword;
    UserViewModel userViewModel;
    DatasetViewModel datasetViewModel;
    UserModel userModel;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = datasetViewModel.getCurrentUser();
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

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        datasetViewModel = new ViewModelProvider(this).get(DatasetViewModel.class);

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

                int minLengthPassword = 6;
                int maxLengthPassword = 20;
                String validationMessage = "La contraseña debe tener entre " + minLengthPassword +
                        " y " + maxLengthPassword + " caracteres";

                if (name.isEmpty() || age.isEmpty() || email.isEmpty() || confirmEmail.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    showDialogCompleteAll();
                } else if (!isValidEmailFormat(email)) {
                    Toast.makeText(getApplicationContext(), "Escriba un correo válido!!!", Toast.LENGTH_LONG).show();
                } else if (!(email.equals(confirmEmail))) {
                    Toast.makeText(getApplicationContext(), "Los correos no coinciden!!!", Toast.LENGTH_LONG).show();
                } else if (!isValidLengthPassword(password, minLengthPassword, maxLengthPassword)) {
                    Toast.makeText(getApplicationContext(), validationMessage, Toast.LENGTH_LONG).show();
                } else if (!(password.equals(confirmPassword))) {
                    showDialogContrasenaNoCoincide();
                } else if (!(cbxTerminosCondiciones.isChecked())) {
                    showDialogAceptaCondicionesYTerminos();
                } else if (!(cbxPoliticasPrivacidad.isChecked())) {
                    showDialogAceptaPrivacidad();
                } else {
                    Integer userAge = Integer.parseInt(age);
                    userModel = new UserModel(name, userAge, email, password);
                    createUser(userModel.getName(), userModel.getAge(), userModel.getEmail(), userModel.getPassword(), getApplicationContext());

                    showDialogoRegistroCompleto();
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
                //startActivity(initIntents().get(0));
            }
        });
        builder.setView(R.layout.dialog_registro_completo).create().show();
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

    private void createUser(String name, Integer age, String email, String password, Context context) {
        userViewModel.createUser(name, age, email, password, context);
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

    private boolean isValidLengthPassword(String password, int min, int max) {
        int minLength = min;
        int maxLength = max;
        return password.length() >= minLength && password.length() <= maxLength;
    }

    private boolean isValidEmailFormat(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}