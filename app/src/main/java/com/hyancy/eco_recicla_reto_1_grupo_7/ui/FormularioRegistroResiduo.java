package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.SpinnerModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.WasteModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.WasteViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FormularioRegistroResiduo extends AppCompatActivity {
    private Button btnCalculatePoints, btnRegisterWaste, btnCancelRegister;
    private ImageView categoriasBottomBar, estadisticasBottomBar, consejosBottomBar, homeAppBottomBar, logoutBottomBar, ivCamera, ivCalendar, ivLocation, photo;
    private EditText edtDescription, edtDateRegister, edtLocationRegister, edtQuantity;
    private LinearLayout lnLayoutRegisterCancelButtons;
    TextView tvPoints;
    double totalPoints = 0;
    private Spinner categoryWasteSpinner;
    private ArrayList<String> listCategories;
    private WasteViewModel wasteViewModel;
    WasteModel wasteModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_registro_residuo);

        SpinnerModel spinnerModel = new SpinnerModel();
        wasteViewModel = new ViewModelProvider(this).get(WasteViewModel.class);

        initComponents();
        calculateWastePoints();
        listenersMenuAppBar();
        spinnerUI(spinnerModel);
    }

    private void initComponents() {
        edtDescription = findViewById(R.id.edt_descripcion_waste);
        edtDateRegister = findViewById(R.id.edt_date_register_waste);
        edtLocationRegister = findViewById(R.id.edt_location_register_waste);
        edtQuantity = findViewById(R.id.edt_quantity_waste);
        tvPoints = findViewById(R.id.tv_points_waste);
        categoryWasteSpinner = findViewById(R.id.spn_category_waste);
        ivCamera = findViewById(R.id.iv_camera_photo);
        ivCalendar = findViewById(R.id.iv_calendar);
        ivLocation = findViewById(R.id.iv_location);
        photo = findViewById(R.id.iv_photo_waste);

        btnCalculatePoints = findViewById(R.id.btn_calculate_points);
        lnLayoutRegisterCancelButtons = findViewById(R.id.ln_layout_register_cancel_buttons);
        btnRegisterWaste = findViewById(R.id.btn_register_waste);
        btnCancelRegister = findViewById(R.id.btn_cancel);

        homeAppBottomBar = findViewById(R.id.home_menu_bottom_bar);
        categoriasBottomBar = findViewById(R.id.categorias_menu_bottom_bar);
        estadisticasBottomBar = findViewById(R.id.estadisticas_menu_bottom_bar);
        consejosBottomBar = findViewById(R.id.consejos_menu_bottom_bar);
        logoutBottomBar = findViewById(R.id.logout_menu_bottom_bar);

    }

    private void listenersMenuAppBar() {
        homeAppBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(0));
            }
        });
        categoriasBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(initIntents().get(1));
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

    private void calculateWastePoints() {
        btnCalculatePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = edtDescription.getText().toString().trim();
                String photoUrl = edtDescription.getText().toString().trim();
                String registerDate = edtDateRegister.getText().toString().trim();
                String location = edtLocationRegister.getText().toString().trim();
                String category = categoryWasteSpinner.getSelectedItem().toString();
                double quantity = Double.parseDouble(edtQuantity.getText().toString().trim());

                if (TextUtils.isEmpty(description)) {
                    Toast.makeText(getApplicationContext(), "Ingrese la descripción del residuo", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(photoUrl)) {
                    Toast.makeText(getApplicationContext(), "Tomar una foto del residuo", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(registerDate)) {
                    Toast.makeText(getApplicationContext(), "Ingrese la fecha de recolección", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(location)) {
                    Toast.makeText(getApplicationContext(), "Ingrese el sitio de recolección", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(category) || category.equals("Seleccione una categoría")) {
                    Toast.makeText(getApplicationContext(), "Seleccione la categoria del residuo", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(String.valueOf(quantity)) || quantity == 0) {
                    Toast.makeText(getApplicationContext(), "Ingrese la cantidad de residuo", Toast.LENGTH_LONG).show();
                    return;
                }
                double environmentFactor = calculateEnvioronmentalFactor(category);
                double recyclingFactor = calculateRecyclingFactor(category);
                int points = (int) Math.round(calculatePoints(environmentFactor, recyclingFactor, quantity));

                wasteModel = new WasteModel(description, photoUrl, registerDate, location, category, quantity, points);

                tvPoints.setText(String.valueOf(points));

                showRegisterCancelButtons(description, photoUrl, registerDate, location, category, quantity, points);
            }
        });
    }

    public void registerWaste(String description, String photoUrl, String registerDate,
                              String location, String category, double quantity, int points) {
        btnRegisterWaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wasteViewModel.createWaste(description, photoUrl, registerDate, location, category, quantity, points);
                //clearComponents();
                //startActivity(initIntents().get(1));
            }
        });

        btnCancelRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalculateButton();
            }
        });
    }

    private double calculatePoints(double environmentalImpactFactor_, double recyclingFactor_, double quantity_) {

        double environmentalImpactFactor = environmentalImpactFactor_;
        double recyclingFactor = recyclingFactor_;
        double quantity = quantity_;

        double points = environmentalImpactFactor * quantity / recyclingFactor;
        return points;
    }

    private double calculateRecyclingFactor(String category) {
        double recyclingFactor = 0;

        Map<String, Integer> numFactor = new HashMap<>();
        numFactor.put("Aceites", 4);
        numFactor.put("Baterías / Pilas", 5);
        numFactor.put("Maderas / Escombros", 2);
        numFactor.put("Metales", 7);
        numFactor.put("Papel / Cartón", 5);
        numFactor.put("Plásticos", 5);
        numFactor.put("Tetrabrik", 3);
        numFactor.put("Vidrios", 7);
        numFactor.put("Orgánicos", 5);

        if (numFactor.containsKey(category)) {
            recyclingFactor = numFactor.get(category);
        }

        return recyclingFactor;
    }

    private double calculateEnvioronmentalFactor(String category) {
        double environmentalFactor = 0;

        Map<String, Integer> numFactor = new HashMap<>();
        numFactor.put("Aceites", 8);
        numFactor.put("Baterías / Pilas", 9);
        numFactor.put("Maderas / Escombros", 5);
        numFactor.put("Metales", 7);
        numFactor.put("Papel / Cartón", 4);
        numFactor.put("Plásticos", 6);
        numFactor.put("Tetrabrik", 4);
        numFactor.put("Vidrios", 3);
        numFactor.put("Orgánicos", 2);

        if (numFactor.containsKey(category)) {
            environmentalFactor = numFactor.get(category);
        }

        return environmentalFactor;
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentPrincipal = new Intent(FormularioRegistroResiduo.this, Principal.class);
        Intent intentCategorias = new Intent(FormularioRegistroResiduo.this, Categoria.class);
        Intent intentEstadisticas = new Intent(FormularioRegistroResiduo.this, Statistic.class);
        Intent intentConsejos = new Intent(FormularioRegistroResiduo.this, Consejos.class);
        Intent intentLogout = new Intent(FormularioRegistroResiduo.this, Index.class);

        listaIntents.add(intentPrincipal);
        listaIntents.add(intentCategorias);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);

        return listaIntents;
    }


    private void showRegisterCancelButtons(String description, String photoUrl, String registerDate,
                                           String location, String category, double quantity, int points) {
        registerWaste(description, photoUrl, registerDate, location, category, quantity, points);
        btnCalculatePoints.setVisibility(View.GONE);
        lnLayoutRegisterCancelButtons.setVisibility(View.VISIBLE);
        btnRegisterWaste.setVisibility(View.VISIBLE);
        btnCancelRegister.setVisibility(View.VISIBLE);
    }

    private void showCalculateButton() {
        btnRegisterWaste.setVisibility(View.GONE);
        btnCancelRegister.setVisibility(View.GONE);
        lnLayoutRegisterCancelButtons.setVisibility(View.VISIBLE);
        btnCalculatePoints.setVisibility(View.VISIBLE);
    }


    private void spinnerUI(SpinnerModel spinnerModel) {
        this.listCategories = spinnerModel.getListSpinnerCategory();
        ArrayAdapter<String> categories = new ArrayAdapter<>(this, R.layout.spinner_categories_item);
        categories.addAll(listCategories);
        categoryWasteSpinner.setAdapter(categories);
    }

    private void clearComponents() {
        edtDescription.setText("");
        edtDateRegister.setText("");
        edtLocationRegister.setText("");
        edtQuantity.setText("0");
        tvPoints.setText("0");
        categoryWasteSpinner.setSelection(0);
    }

}