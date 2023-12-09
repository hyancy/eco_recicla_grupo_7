package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.WasteModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.MyViewModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.WasteViewModel;

import java.util.ArrayList;

public class Categoria extends AppCompatActivity {
    private CardView cardAceites, cardBateriasPilas, cardMaderasEscombros, cardMetales, cardPapelCarton, cardPlasticos,
            cardTetrabrik, cardVidrios, cardOrganicos;
    private TextView tvCardAceites, tvCardBateriasPilas, tvCardMaderasEscombros, tvCardMetales, tvCardPapelCarton, tvCardPlasticos,
            tvCardTetrabrik, tvCardVidrios, tvCardOrganicos;
    private ImageView estadisticasBottomBar, consejosBottomBar, homeAppBottomBar, logoutBottomBar;
    private MyViewModel viewModel;
    private WasteViewModel wasteViewModel;
    private UserViewModel userViewModel;
    private ArrayList<QueryDocumentSnapshot> wasteList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        wasteViewModel = new ViewModelProvider(this).get(WasteViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

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

        tvCardAceites = findViewById(R.id.tv_aceites);
        tvCardBateriasPilas = findViewById(R.id.tv_baterias_pilas);
        tvCardMaderasEscombros = findViewById(R.id.tv_maderas_escombros);
        tvCardMetales = findViewById(R.id.tv_metales);
        tvCardPapelCarton = findViewById(R.id.tv_papel_carton);
        tvCardPlasticos = findViewById(R.id.tv_plasticos);
        tvCardTetrabrik = findViewById(R.id.tv_tetrabrick);
        tvCardVidrios = findViewById(R.id.tv_vidrios);
        tvCardOrganicos = findViewById(R.id.tv_organicos);
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
                System.out.println("CATEGORIA: " + tvCardBateriasPilas.getText().toString().trim());
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
                userViewModel.logoutSesion();
                startActivity(initIntents().get(4));
            }
        });
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
                startActivity(initIntents().get(1));
                dialog.dismiss();
            }
        });

        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(2));
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
        Intent intentPrincipal = new Intent(Categoria.this, Principal.class);
        Intent intentRegistroResiduos = new Intent(Categoria.this, FormularioRegistroResiduo.class);
        Intent intentEstadisticas = new Intent(Categoria.this, Statistic.class);
        Intent intentConsejos = new Intent(Categoria.this, Consejos.class);
        Intent intentLogout = new Intent(Categoria.this, Login.class);

        listaIntents.add(intentPrincipal);
        listaIntents.add(intentRegistroResiduos);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);

        return listaIntents;
    }
}