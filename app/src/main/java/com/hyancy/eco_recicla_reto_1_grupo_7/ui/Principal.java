package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Button btnCategorias, btnEstadistica, btnConsejos;
    private ImageView imageViewToolbar, ivUser;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView tvUserCurrent;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_principal_menu);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        setToolbar();
        initComponents();
        listenersButtons();
        showCurrentSesion();
    }

    // application

    private void initComponents() {
        btnCategorias = findViewById(R.id.btn_categorias);
        btnEstadistica = findViewById(R.id.btn_estadistica);
        btnConsejos = findViewById(R.id.btn_consejos);
        imageViewToolbar = findViewById(R.id.menu_hamburguesa);
        tvUserCurrent = findViewById(R.id.user_current);
        ivUser = findViewById(R.id.icono_usuario_pantalla_principal);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.menu_navigation);

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void listenersButtons() {
        btnCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        btnEstadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
        btnConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(2));
            }
        });
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, MiPerfil.class);
                startActivity(intent);
            }
        });

        imageViewToolbar.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.END);
        });
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentCategorias = new Intent(Principal.this, Categoria.class);
        Intent intentEstadisticas = new Intent(Principal.this, Statistic.class);
        Intent intentConsejos = new Intent(Principal.this, Consejos.class);
        Intent intentLogout = new Intent(Principal.this, Index.class);
        Intent intentPrincipal = new Intent(Principal.this, Principal.class);
        Intent intentInfoApp = new Intent(Principal.this, InformacionApp.class);

        listaIntents.add(intentCategorias);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);
        listaIntents.add(intentPrincipal);
        listaIntents.add(intentInfoApp);

        return listaIntents;
    }

    private void showCurrentSesion() {
        if (currentUser == null) {
            Intent intentLogin = new Intent(getApplicationContext(), Login.class);
            startActivity(intentLogin);
            finish();
        } else {
            tvUserCurrent.setText(currentUser.getEmail());

        }
    }

    private void logoutCurrentSesion() {
        userViewModel.logoutSesion();
        Intent intentLogout = new Intent(getApplicationContext(), Index.class);
        startActivity(intentLogout);
        finish();
    }

    //Toolbar
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_inicio) {
            drawerLayout.closeDrawer(GravityCompat.END);
            return true;
        } else if (item.getItemId() == R.id.menu_categorias) {
            startActivity(initIntents().get(0));
            return true;
        } else if (item.getItemId() == R.id.menu_estadisticas) {
            startActivity(initIntents().get(1));
            return true;
        } else if (item.getItemId() == R.id.menu_consejos) {
            startActivity(initIntents().get(2));
            return true;
        } else if (item.getItemId() == R.id.menu_info_app) {
            startActivity(initIntents().get(5));
            return true;
        } else if (item.getItemId() == R.id.menu_cerrar_sesion) {
            logoutCurrentSesion();
            return true;
        }

        return false;
    }


}