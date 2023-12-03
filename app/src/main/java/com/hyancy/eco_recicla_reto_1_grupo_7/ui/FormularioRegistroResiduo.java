package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;

import java.util.ArrayList;

public class FormularioRegistroResiduo extends AppCompatActivity {
    Button btnBegistarResiduo;
    ImageView categoriasBottomBar, estadisticasBottomBar, consejosBottomBar, infoAppBottomBar, logoutBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_registro_residuo);

        initComponents();
        listenersButtons();
    }

    private void initComponents() {
        btnBegistarResiduo = findViewById(R.id.btn_registrar_residuo);

        categoriasBottomBar = findViewById(R.id.categorias_menu_bottom_bar);
        estadisticasBottomBar = findViewById(R.id.estadisticas_menu_bottom_bar);
        consejosBottomBar = findViewById(R.id.consejos_menu_bottom_bar);
        infoAppBottomBar = findViewById(R.id.info_app_menu_bottom_bar);
        logoutBottomBar = findViewById(R.id.logout_menu_bottom_bar);
    }

    private void listenersButtons() {
        btnBegistarResiduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
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
                startActivity(initIntents().get(2));
            }
        });
        infoAppBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        logoutBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(3));
            }
        });
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentCategorias = new Intent(FormularioRegistroResiduo.this, Categoria.class);
        Intent intentEstadisticas = new Intent(FormularioRegistroResiduo.this, Statistic.class);
        Intent intentConsejos = new Intent(FormularioRegistroResiduo.this, Consejos.class);
        Intent intentLogout= new Intent(FormularioRegistroResiduo.this, MainActivity.class);

        listaIntents.add(intentCategorias);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);

        return listaIntents;
    }
}