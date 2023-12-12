package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.WasteViewModel;

import java.util.ArrayList;

public class Categoria extends AppCompatActivity {
    private SearchView searchFilterCategory;
    private CardView cardsLists[];
    private TextView tvCardsList[];
    private ImageView imageViewToolbar;
    private CardView cardAceites, cardBateriasPilas, cardMaderasEscombros, cardMetales, cardPapelCarton, cardPlasticos,
            cardTetrabrik, cardVidrios, cardOrganicos;
    private TextView tvCardAceites, tvCardBateriasPilas, tvCardMaderasEscombros, tvCardMetales, tvCardPapelCarton, tvCardPlasticos,
            tvCardTetrabrik, tvCardVidrios, tvCardOrganicos, tvCategoryNotFound;
    private ImageView estadisticasBottomBar, consejosBottomBar, homeAppBottomBar, logoutBottomBar;
    private GridLayout gridLayoutCards;
    private WasteViewModel wasteViewModel;
    private UserViewModel userViewModel;
    private ArrayList<QueryDocumentSnapshot> wasteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        wasteViewModel = new ViewModelProvider(this).get(WasteViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        setToolbar();
        initComponents();
        listenersCards();
        listenersMenuAppBar();
        filterCard();
    }

    private void initComponents() {
        searchFilterCategory = findViewById(R.id.srch_filter_category);
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
        imageViewToolbar = findViewById(R.id.menu_hamburguesa);

        tvCardAceites = findViewById(R.id.tv_aceites);
        tvCardBateriasPilas = findViewById(R.id.tv_baterias_pilas);
        tvCardMaderasEscombros = findViewById(R.id.tv_maderas_escombros);
        tvCardMetales = findViewById(R.id.tv_metales);
        tvCardPapelCarton = findViewById(R.id.tv_papel_carton);
        tvCardPlasticos = findViewById(R.id.tv_plasticos);
        tvCardTetrabrik = findViewById(R.id.tv_tetrabrick);
        tvCardVidrios = findViewById(R.id.tv_vidrios);
        tvCardOrganicos = findViewById(R.id.tv_organicos);
        tvCategoryNotFound = findViewById(R.id.tv_category_not_found);

        gridLayoutCards = findViewById(R.id.grid_layout_cards);

        cardsLists = initCardsList();
        tvCardsList = initTvCardsList();

    }

    private TextView[] initTvCardsList() {
        TextView tvCards[] = {tvCardAceites, tvCardBateriasPilas, tvCardMaderasEscombros, tvCardMetales,
                tvCardPapelCarton, tvCardPlasticos, tvCardTetrabrik, tvCardVidrios, tvCardOrganicos};
        return tvCards;
    }

    private CardView[] initCardsList() {
        CardView cards[] = {cardAceites, cardBateriasPilas, cardMaderasEscombros, cardMetales,
                cardPapelCarton, cardPlasticos, cardTetrabrik, cardVidrios, cardOrganicos};
        return cards;
    }

    private void listenersCards() {
        cardAceites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCard(tvCardAceites.getText().toString());
            }
        });

        cardBateriasPilas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCard(tvCardBateriasPilas.getText().toString().trim());
            }
        });

        cardMaderasEscombros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCard(tvCardMaderasEscombros.getText().toString());
            }
        });
        cardMetales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCard(tvCardMetales.getText().toString());
            }
        });
        cardPapelCarton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCard(tvCardPapelCarton.getText().toString());
            }
        });
        cardPlasticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCard(tvCardPlasticos.getText().toString());
            }
        });
        cardTetrabrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCard(tvCardTetrabrik.getText().toString());
            }
        });
        cardVidrios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCard(tvCardVidrios.getText().toString());
            }
        });
        cardOrganicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCard(tvCardOrganicos.getText().toString());
            }
        });

    }

    private void listenersMenuAppBar() {
        homeAppBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(4));
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

    private void filterCard() {
        searchFilterCategory.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchFilterListener(newText.toString());
                return false;
            }
        });
    }
    private void searchFilterListener(String filter) {
        int lengthFilter = filter.length();
        int goneCount = 0;
        if (lengthFilter != 0) {
            for (int i = 0; i < tvCardsList.length; i++) {
                if (tvCardsList[i].getText().toString().toLowerCase().contains(filter.toLowerCase())) {
                    tvCategoryNotFound.setVisibility(View.GONE);
                    gridLayoutCards.setVisibility(View.VISIBLE);
                    cardsLists[i].setVisibility(View.VISIBLE);
                }
                if (!tvCardsList[i].getText().toString().toLowerCase().contains(filter.toLowerCase())) {
                    cardsLists[i].setVisibility(View.GONE);
                    goneCount++;
                }
                if (goneCount == 9) {
                    gridLayoutCards.setVisibility(View.GONE);
                    tvCategoryNotFound.setVisibility(View.VISIBLE);
                }
            }

        } else {
            for (CardView card : cardsLists) {
                card.setVisibility(View.VISIBLE);
            }
        }
    }

    private void showDialogCard(String category) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_cards_resume, null);

        dialogBuilder.setView(view);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        TextView tvTitleDialogCards = view.findViewById(R.id.tv_title_dialog_cards);
        TextView tvAccumulatedAmount = view.findViewById(R.id.tv_accumulated_amount);
        TextView tvAccumulatedPoints = view.findViewById(R.id.tv_accumulated_points);

        Button btnStatistics = view.findViewById(R.id.btn_statistics);
        Button btnNewRegister = view.findViewById(R.id.btn_new_register);

        wasteViewModel.getWasteByUserId(userViewModel.getUidUser().toString(), wasteList, tvAccumulatedAmount,
                tvAccumulatedPoints, category);
        //int points = getTotalPoints(wasteList);
        //double quantity = getTotalQuantity(wasteList);


        tvTitleDialogCards.setText(category);
        //tvAccumulatedAmount.setText(String.valueOf(quantity));
        // tvAccumulatedPoints.setText(String.valueOf(points));

        btnNewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(5).putExtra("category", category));
                dialog.dismiss();
            }
        });

        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
                dialog.dismiss();
            }
        });

    }

    public Integer getTotalPoints(ArrayList<QueryDocumentSnapshot> wasteList) {
        Integer points = 0;
        if (!wasteList.isEmpty()) {
            for (QueryDocumentSnapshot waste : wasteList) {
                points += Integer.parseInt(waste.get("points").toString());
            }
        } else {
            Toast.makeText(this, "No existe información", Toast.LENGTH_LONG).show();
        }
        return points;
    }

    private double getTotalQuantity(ArrayList<QueryDocumentSnapshot> wasteList) {
        Double quantity = 0.0;
        if (!wasteList.isEmpty()) {
            for (QueryDocumentSnapshot waste : wasteList) {
                quantity += Double.parseDouble(waste.getString("quantity"));
            }
        } else {
            Toast.makeText(this, "No existe información", Toast.LENGTH_LONG).show();
        }
        return quantity;
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentCategorias = new Intent(Categoria.this, Categoria.class);
        Intent intentEstadisticas = new Intent(Categoria.this, Statistic.class);
        Intent intentConsejos = new Intent(Categoria.this, Consejos.class);
        Intent intentLogout = new Intent(Categoria.this, Index.class);
        Intent intentPrincipal = new Intent(Categoria.this, Principal.class);
        Intent intentRegisterWaste = new Intent(Categoria.this, FormularioRegistroResiduo.class);

        listaIntents.add(intentCategorias);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);
        listaIntents.add(intentPrincipal);
        listaIntents.add(intentRegisterWaste);

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_foreground);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lateral, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_inicio) {
            startActivity(initIntents().get(4));
        }
        if (item.getItemId() == R.id.menu_categorias) {
            item.collapseActionView();
        }
        if (item.getItemId() == R.id.menu_estadisticas) {
            startActivity(initIntents().get(1));
        }
        if (item.getItemId() == R.id.menu_consejos) {
            startActivity(initIntents().get(2));
        }
        if (item.getItemId() == R.id.menu_info_app) {
            Toast.makeText(this, "Información de la App", Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.menu_cerrar_sesion) {
            logoutCurrentSesion();
        }

        return super.onOptionsItemSelected(item);
    }
}