package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.SpinnerModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.WasteModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;
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
    private UserViewModel userViewModel;
    WasteModel wasteModel;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_registro_residuo);

        SpinnerModel spinnerModel = new SpinnerModel();
        wasteViewModel = new ViewModelProvider(this).get(WasteViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        bundle = getIntent().getExtras();

        setToolbar();
        initComponents();
        setlisteners();
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

    private void setlisteners() {
        ivCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCalendar();
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
        logoutBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutCurrentSesion();
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
                double quantity = 0;

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
                quantity = Double.parseDouble(edtQuantity.getText().toString().trim());

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
                tvPoints.setText("0");
                edtQuantity.setHint("0");
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
        String category = bundle.getString("category");
        this.listCategories = spinnerModel.getListSpinnerCategory();
        ArrayAdapter<String> categories = new ArrayAdapter<>(this, R.layout.spinner_categories_item);
        categories.addAll(listCategories);
        categoryWasteSpinner.setAdapter(categories);
        categoryWasteSpinner.setSelection(categories.getPosition(category));
    }

    private void clearComponents() {
        edtDescription.setText("");
        edtDateRegister.setText("");
        edtLocationRegister.setText("");
        edtQuantity.setHint("0");
        tvPoints.setText("0");
        categoryWasteSpinner.setSelection(0);
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentCategorias = new Intent(FormularioRegistroResiduo.this, Categoria.class);
        Intent intentEstadisticas = new Intent(FormularioRegistroResiduo.this, Statistic.class);
        Intent intentConsejos = new Intent(FormularioRegistroResiduo.this, Consejos.class);
        Intent intentLogout = new Intent(FormularioRegistroResiduo.this, Index.class);
        Intent intentPrincipal = new Intent(FormularioRegistroResiduo.this, Principal.class);

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
        } else if (item.getItemId() == R.id.menu_categorias) {
            startActivity(initIntents().get(0));
        } else if (item.getItemId() == R.id.menu_estadisticas) {
            startActivity(initIntents().get(1));
        } else if (item.getItemId() == R.id.menu_consejos) {
            startActivity(initIntents().get(2));
        } else if (item.getItemId() == R.id.menu_info_app) {
            Toast.makeText(this, "Información de la App", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.menu_cerrar_sesion) {
            logoutCurrentSesion();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialogCalendar() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_calendar, null);

        dialogBuilder.setView(view);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        DatePicker calendar = view.findViewById(R.id.calendar);
        int day = calendar.getDayOfMonth();
        int month = calendar.getMonth();
        int year = calendar.getYear();

        calendar.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            }
        });
    }

}