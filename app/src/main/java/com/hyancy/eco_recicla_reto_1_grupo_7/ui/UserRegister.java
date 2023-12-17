package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
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

public class UserRegister extends AppCompatActivity {
    private FloatingActionButton btnCerrar;
    private CheckBox cbxTerminosCondiciones, cbxPoliticasPrivacidad;
    private Button btnRegistrarUsuario;
    private ProgressBar progressBar;
    private TextView tvYaTieneCuenta;
    private EditText edtName, edtAge, edtEmail, edtConfirmEmail, edtPassword, edtConfirmPassword;
    private UserViewModel userViewModel;
    private DatasetViewModel datasetViewModel;
    private UserModel userModel;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = datasetViewModel.getCurrentUser();
        if (currentUser != null) {
            Intent intentPrincipal = new Intent(getApplicationContext(), Main.class);
            startActivity(intentPrincipal);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

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
                    cbxTerminosCondiciones.setChecked(false);
                }
            }
        });

        cbxPoliticasPrivacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbxPoliticasPrivacidad.isChecked()) {
                    showDialogPrivacidad();
                } else {
                    cbxPoliticasPrivacidad.setChecked(false);
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
                    showDialogCorreoValido();
                } else if (!(email.equals(confirmEmail))) {
                    showDialogCorreoNoCoinciden();
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
        View view = getLayoutInflater().inflate(R.layout.dialog_complete_all_registration_information, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        Button btnAceptarTodo = view.findViewById(R.id.btn_aceptar_todo);

        btnAceptarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void showDialogoRegistroCompleto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_register_complete, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button btnAceptarTodoRegistro = view.findViewById(R.id.btn_aceptar_registro_completo);

        btnAceptarTodoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(UserRegister.this, Main.class);
                startActivity(intent);

            }
        });
    }

    private void showDialogContrasenaNoCoincide() {
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       View view = getLayoutInflater().inflate(R.layout.dialog_passwords_no_match, null);
       builder.setView(view);

       AlertDialog dialog = builder.create();
       dialog.show();

       Button btnAceptarNoCoincideContrasena = view.findViewById(R.id.btn_aceptar_no_coincide_contrasena);
       btnAceptarNoCoincideContrasena.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dialog.dismiss();
           }
       });

    }

    private void showDialogAceptaCondicionesYTerminos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_accept_terms_conditions, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button btnAceptaTerminos = view.findViewById(R.id.btn_aceptar_terminos);

        btnAceptaTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showDialogTerminosYCondicones() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_terms_conditions, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button btnNoAceptarCondiones = view.findViewById(R.id.btn_no_aceptar_condiciones);
        Button btnAceptarCondiciones = view.findViewById(R.id.btn_aceptar_condiciones);

        btnNoAceptarCondiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                cbxTerminosCondiciones.setChecked(false);
            }
        });
        btnAceptarCondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                cbxTerminosCondiciones.isChecked();
            }
        });
    }

    private void showDialogAceptaPrivacidad() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_accept_privacy_policies, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button btnAceptaDatos = view.findViewById(R.id.btn_aceptar_datos);

        btnAceptaDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    private void showDialogPrivacidad() {
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       View view = getLayoutInflater().inflate(R.layout.dialog_privacity, null);
       builder.setView(view);

       AlertDialog dialog = builder.create();
       dialog.show();

       Button btnAceptaPrivacidad = view.findViewById(R.id.btn_aceptar_privacidad);
       Button btnNoAceptaPrivacidad = view.findViewById(R.id.btn_no_aceptar_privacidad);

       btnAceptaPrivacidad.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dialog.dismiss();
               cbxPoliticasPrivacidad.isChecked();
           }
       });
       btnNoAceptaPrivacidad.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dialog.dismiss();
               cbxPoliticasPrivacidad.setChecked(false);
           }
       });
    }
    private void showDialogCorreoNoCoinciden(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_emails_no_match, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button btnAceptarCorreoNoCoinciden = view.findViewById(R.id.btn_aceptar_no_coincide_correos);

        btnAceptarCorreoNoCoinciden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    private void showDialogCorreoValido(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_email_invalid, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button btnAceptarCorreoValido = view.findViewById(R.id.btn_aceptar_correo_invalido);

        btnAceptarCorreoValido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void createUser(String name, Integer age, String email, String password, Context context) {
        userViewModel.createUser(name, age, email, password, context);
    }


    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentHome = new Intent(UserRegister.this, Index.class);
        Intent intentTerminosCondiciones = new Intent(UserRegister.this, PrivacyPoliceTermsConditions.class);
        Intent intentLogin = new Intent(UserRegister.this, Login.class);

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
