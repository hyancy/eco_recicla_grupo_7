package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.navigation.NavigationView;
import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.CategoriesModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.WasteModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.WasteViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class WasteRegisterForm extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Button btnCalculatePoints, btnRegisterWaste, btnCancelRegister;
    private ImageView categoriasBottomBar, estadisticasBottomBar, consejosBottomBar, homeAppBottomBar, logoutBottomBar, ivCamera, ivCalendar, ivLocation, photo;
    private EditText edtDescription, edtDateRegister, edtLocationRegister, edtQuantity;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageViewToolbar;
    private DatePicker calendar;
    private LinearLayout lnLayoutRegisterCancelButtons;
    private TextView tvPoints;
    double totalPoints = 0;
    private Spinner categoryWasteSpinner;
    private ArrayList<String> listCategories;
    private WasteViewModel wasteViewModel;
    private UserViewModel userViewModel;
    private WasteModel wasteModel;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_formulario_registro_residuos);

        CategoriesModel categoriesModel = new CategoriesModel();
        wasteViewModel = new ViewModelProvider(this).get(WasteViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        bundle = getIntent().getExtras();

        setToolbar();
        initComponents();
        setlisteners();
        calculateWastePoints();
        setDateRegister();
        listenersMenuAppBar();
        spinnerUI(categoriesModel);
    }

    private void initComponents() {
        imageViewToolbar = findViewById(R.id.menu_hamburguesa);
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

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.menu_navigation);

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setlisteners() {
        ivCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCalendar();
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setDateRegister();
            }
        });

        imageViewToolbar.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.END);
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
                String quantity_ = edtQuantity.getText().toString().trim();

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

                if(TextUtils.isEmpty(quantity_)){
                    Toast.makeText(getApplicationContext(), "Ingrese la cantidad de residuo", Toast.LENGTH_LONG).show();
                    return;
                }
                quantity = Double.parseDouble(quantity_);
                if (quantity == 0) {
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
                clearComponents();
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


    private void spinnerUI(CategoriesModel categoriesModel) {
        String category = bundle.getString("category");
        this.listCategories = categoriesModel.getListCategories();
        ArrayAdapter<String> categories = new ArrayAdapter<>(this, R.layout.spinner_categories_item);
        categories.addAll(listCategories);
        categoryWasteSpinner.setAdapter(categories);
        categoryWasteSpinner.setSelection(categories.getPosition(category));
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

    private void setDateRegister() {
        final Calendar calendar_ = Calendar.getInstance();
        int mYear = calendar_.get(Calendar.YEAR);
        int mMonth = calendar_.get(Calendar.MONTH);
        int mDay = calendar_.get(Calendar.DAY_OF_MONTH);
        String dateReg = mDay + "/" + (mMonth +1 ) + "/" + mYear;

        edtDateRegister.setText(dateReg);
    }


    private void setDateRegister_() {
        DatePickerDialog datePickerDialog;
        View view = getLayoutInflater().inflate(R.layout.dialog_calendar, null);

        final Calendar calendar_ = Calendar.getInstance();
        int mYear = calendar_.get(Calendar.YEAR);
        int mMonth = calendar_.get(Calendar.MONTH);
        int mDay = calendar_.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int monthReg_ = month + 1;
                String dateReg_ = dayOfMonth + "/" + monthReg_ + "/" + year;
                edtDateRegister.setText(dateReg_);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.setView(view);
        datePickerDialog.show();
    }


    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentCategorias = new Intent(WasteRegisterForm.this, Category.class);
        Intent intentEstadisticas = new Intent(WasteRegisterForm.this, Statistic.class);
        Intent intentConsejos = new Intent(WasteRegisterForm.this, Tips.class);
        Intent intentLogout = new Intent(WasteRegisterForm.this, Index.class);
        Intent intentPrincipal = new Intent(WasteRegisterForm.this, Main.class);
        Intent intentInfoApp = new Intent(WasteRegisterForm.this, AppInformation.class);

        listaIntents.add(intentCategorias);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);
        listaIntents.add(intentPrincipal);
        listaIntents.add(intentInfoApp);

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

    private void showDialogCalendar() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_calendar, null);

        dialogBuilder.setView(view);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        calendar = view.findViewById(R.id.calendar);

        calendar.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String dayRegister = String.valueOf(dayOfMonth);
                String monthRegister = String.valueOf(monthOfYear + 1);
                String yearRegister = String.valueOf(year);
                String dateRegister = dayRegister + "/" + monthRegister + "/" + yearRegister;

                edtDateRegister.setText(dateRegister);

                dialog.dismiss();
            }
        });
    }

    private void showDialogCompleteDataForm() {
        AlertDialog.Builder dialogCompleteDataForm = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_complete_data_form, null);
        dialogCompleteDataForm.setView(view);
        final AlertDialog dialog = dialogCompleteDataForm.create();
        dialog.show();
    }

    private void clearComponents() {
        edtDescription.setText("");
        setDateRegister();
        edtLocationRegister.setText("");
        edtQuantity.setHint("0");
        edtQuantity.setText(null);
        tvPoints.setText("0");
        categoryWasteSpinner.setSelection(0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_inicio) {
            startActivity(initIntents().get(4));
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