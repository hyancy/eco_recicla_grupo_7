package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.ui.models.WasteModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.MyViewModel;

import java.util.ArrayList;

public class Categoria extends AppCompatActivity {
    CardView cardAceites, cardBateriasPilas, cardMaderasEscombros, cardMetales, cardPapelCarton, cardPlasticos,
            cardTetrabrik, cardVidrios, cardOrganicos;
    ImageView estadisticasBottomBar, consejosBottomBar, homeAppBottomBar, logoutBottomBar;
    SearchView filtrarCard;
    MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        initComponents();
        listenersCards();
        listenersMenuAppBar();
        setViewModel();

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
        homeAppBottomBar = findViewById(R.id.home_menu_bottom_bar);
        logoutBottomBar = findViewById(R.id.logout_menu_bottom_bar);
    }

    private void listenersCards() {
        cardAceites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }

        });

        cardBateriasPilas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });

        cardMaderasEscombros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
        cardMetales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
        cardPapelCarton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
        cardPlasticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
        cardTetrabrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
        cardVidrios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });
        cardOrganicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
            }
        });

    }

    private void listenersMenuAppBar() {
        homeAppBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        estadisticasBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(initIntents().get(2));
            }
        });
        consejosBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(3));
            }
        });
        logoutBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(4));
            }
        });
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentPrincipal = new Intent(Categoria.this, Principal.class);
        Intent intentRegistroResiduos = new Intent(Categoria.this, FormularioRegistroResiduo.class);
        Intent intentEstadisticas = new Intent(Categoria.this, Statistic.class);
        Intent intentConsejos = new Intent(Categoria.this, Consejos.class);
        Intent intentLogout = new Intent(Categoria.this, Index.class);

        listaIntents.add(intentPrincipal);
        listaIntents.add(intentRegistroResiduos);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);

        return listaIntents;
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getListProductsLiveData().observe(this, new Observer<ArrayList<WasteModel>>() {
            @Override
            public void onChanged(ArrayList<WasteModel> wasteModels) {
                for (WasteModel wasteModel : wasteModels) {
                    System.out.println("Lista de productos: " + wasteModel.getCategory());
                }
            }
        });
    }
}