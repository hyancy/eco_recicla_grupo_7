package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.ui.models.SpinnerModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.ui.models.WasteModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.WasteViewModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FormularioRegistroResiduo extends AppCompatActivity {
    private Button btnBegistarResiduo;
    private ImageView categoriasBottomBar, estadisticasBottomBar, consejosBottomBar, homeAppBottomBar, logoutBottomBar, ivCamera, ivCalendar, ivLocation, photo;
    private EditText edtDescription, edtDateRegister, edtLocationRegister, edtQuantity;
    TextView tvPoints;
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
        listenersButtons();
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

        btnBegistarResiduo = findViewById(R.id.btn_register_waste);

        homeAppBottomBar = findViewById(R.id.home_menu_bottom_bar);
        categoriasBottomBar = findViewById(R.id.categorias_menu_bottom_bar);
        estadisticasBottomBar = findViewById(R.id.estadisticas_menu_bottom_bar);
        consejosBottomBar = findViewById(R.id.consejos_menu_bottom_bar);
        logoutBottomBar = findViewById(R.id.logout_menu_bottom_bar);

    }

    private void listenersButtons() {
        createWaste();
        listenersMenuAppBar();
    }

    private void createWaste() {
        btnBegistarResiduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = edtDescription.getText().toString().trim();
                String photoUrl = edtDescription.getText().toString().trim();
                String registerDate = edtDateRegister.getText().toString().trim();
                String location = edtLocationRegister.getText().toString().trim();
                String category = categoryWasteSpinner.getSelectedItem().toString();
                double quantity = Double.parseDouble(edtQuantity.getText().toString().trim());
                int points = Integer.parseInt(tvPoints.getText().toString().trim());

                wasteModel = new WasteModel(description, photoUrl, registerDate, location, category, quantity, points);

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

                wasteViewModel.createWaste(description, photoUrl, registerDate, location, category, quantity, points);
                startActivity(initIntents().get(0));
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

    private void spinnerUI(SpinnerModel spinnerModel) {
        this.listCategories = spinnerModel.getListSpinnerCategory();
        ArrayAdapter<String> categories = new ArrayAdapter<>(this, R.layout.spinner_categories_item);
        categories.addAll(listCategories);
        categoryWasteSpinner.setAdapter(categories);
    }

    private void clearComponents() {
    }
}