package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.DatasetViewModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;

import java.util.ArrayList;

public class Consejos extends AppCompatActivity {
    private ImageView categoriasBottomBar, estadisticasBottomBar, consejosBottomBar, homeAppBottomBar, logoutBottomBar;
    private ImageView imageViewToolbar;
    Toolbar toolbar;
    private LinearLayout lnrLMenuBottomBar;
    private View backgroundBottomBar;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private UserViewModel userViewModel;
    private DatasetViewModel datasetViewModel;

    private CardView consejoAceite, consejoPlastico, consejoCompost, consejoRopa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        datasetViewModel = new ViewModelProvider(this).get(DatasetViewModel.class);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        initComponents();
        showMenuBottomAppBar();
        setToolbar();
        listenersMenuAppBar();
        listenersCards();

    }

    private void initComponents() {
        homeAppBottomBar = findViewById(R.id.home_menu_bottom_bar);
        categoriasBottomBar = findViewById(R.id.categorias_menu_bottom_bar);
        estadisticasBottomBar = findViewById(R.id.estadisticas_menu_bottom_bar);
        consejosBottomBar = findViewById(R.id.consejos_menu_bottom_bar);
        logoutBottomBar = findViewById(R.id.logout_menu_bottom_bar);
        imageViewToolbar = findViewById(R.id.menu_hamburguesa);
        consejoAceite = findViewById(R.id.consejo_aceite);
        consejoPlastico = findViewById(R.id.consejo_plastico);
        consejoCompost = findViewById(R.id.consejo_compost);
        consejoRopa = findViewById(R.id.consejo_ropa);

        toolbar = findViewById(R.id.menu_toolbar);

        lnrLMenuBottomBar = findViewById(R.id.linear_layout_menu_bottom_bar);
        backgroundBottomBar = findViewById(R.id.background_bottom_bar);
    }

    private void showMenuBottomAppBar() {
        if (currentUser != null) {
            lnrLMenuBottomBar.setVisibility(View.VISIBLE);
            backgroundBottomBar.setVisibility(View.VISIBLE);
            imageViewToolbar.setVisibility(View.VISIBLE);
        } else {
            lnrLMenuBottomBar.setVisibility(View.INVISIBLE);
            backgroundBottomBar.setVisibility(View.INVISIBLE);
            imageViewToolbar.setVisibility(View.INVISIBLE);
        }
    }

    private void listenersMenuAppBar() {
        homeAppBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(4));
            }
        });
        categoriasBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(initIntents().get(0));
            }
        });
        estadisticasBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(initIntents().get(1));
            }
        });
        consejosBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        logoutBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutCurrentSesion();
            }
        });

        imageViewToolbar.setOnClickListener(v -> {
            Toast.makeText(this, "Menu", Toast.LENGTH_LONG).show();
        });
    }

    private void listenersCards() {
        consejoAceite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConsejoAceite ();
            }
        });

        consejoPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        consejoCompost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        consejoRopa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentCategorias = new Intent(Consejos.this, Categoria.class);
        Intent intentEstadisticas = new Intent(Consejos.this, Statistic.class);
        Intent intentConsejos = new Intent(Consejos.this, Consejos.class);
        Intent intentLogout = new Intent(Consejos.this, Index.class);
        Intent intentPrincipal = new Intent(Consejos.this, Principal.class);

        listaIntents.add(intentCategorias);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);
        listaIntents.add(intentPrincipal);

        return listaIntents;
    }


    private void logoutCurrentSesion() {
        userViewModel.logoutSesion();
        Intent intentLogout = new Intent(getApplicationContext(), Index.class);
        startActivity(intentLogout);
        finish();
    }

    //Toolbar
    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_foreground);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (currentUser == null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (currentUser != null) {
            getMenuInflater().inflate(R.menu.menu_lateral, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(initIntents().get(3));
            finish();
        } else if (item.getItemId() == R.id.menu_inicio) {
            startActivity(initIntents().get(4));
        } else if (item.getItemId() == R.id.menu_categorias) {
            startActivity(initIntents().get(0));
        } else if (item.getItemId() == R.id.menu_estadisticas) {
            startActivity(initIntents().get(1));
        } else if (item.getItemId() == R.id.menu_consejos) {
            item.collapseActionView();
        } else if (item.getItemId() == R.id.menu_info_app) {
            Toast.makeText(this, "Información de la App", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.menu_cerrar_sesion) {
            logoutCurrentSesion();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showConsejoAceite () {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_consejo1, null);

        alertDialogBuilder.setView(view);

        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();

        TextView cerrarConsejo = view.findViewById(R.id.cerrar_consejo);
        cerrarConsejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

}
