package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Categoria extends AppCompatActivity {
    CardView cardAceites, cardBateriasPilas, cardMaderasEscombros, cardMetales, cardPapelCarton, cardPlasticos,
            cardTetrabrik, cardVidrios, cardOrganicos;
    ImageView estadisticasBottomBar, consejosBottomBar, infoAppBottomBar, logoutBottomBar;
    SearchView filtrarCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        initComponents();
        listenersButtons();

    }

    private void initComponents() {
        cardAceites = findViewById(R.id.card_aceites);
        cardBateriasPilas = findViewById(R.id.card_baterias_pilas);
        cardMaderasEscombros = findViewById(R.id.card_maderas_escombros);
        cardMetales = findViewById(R.id.card_metales);
        cardPapelCarton = findViewById(R.id.card_papel_carton);
        cardPlasticos = findViewById(R.id.card_plasticos);
        cardTetrabrik = findViewById(R.id.card_tetrabrik);
        cardVidrios = findViewById(R.id.card_vidrios);
        cardOrganicos = findViewById(R.id.card_organicos);

        estadisticasBottomBar = findViewById(R.id.estadisticas_menu_bottom_bar);
        consejosBottomBar = findViewById(R.id.consejos_menu_bottom_bar);
        infoAppBottomBar = findViewById(R.id.info_app_menu_bottom_bar);
        logoutBottomBar = findViewById(R.id.logout_menu_bottom_bar);

    }

    private void listenersButtons() {
        cardAceites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });

        cardBateriasPilas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });

        cardMaderasEscombros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        cardMetales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        cardPapelCarton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        cardPlasticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        cardTetrabrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        cardVidrios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        cardOrganicos.setOnClickListener(new View.OnClickListener() {
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
        Intent intentRegistroResiduos = new Intent(Categoria.this, FormularioRegistroResiduo.class);
        Intent intentEstadisticas = new Intent(Categoria.this, Statistic.class);
        Intent intentConsejos = new Intent(Categoria.this, Consejos.class);
        Intent intentLogout= new Intent(Categoria.this, MainActivity.class);

        listaIntents.add(intentRegistroResiduos);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);

        return listaIntents;
    }
}