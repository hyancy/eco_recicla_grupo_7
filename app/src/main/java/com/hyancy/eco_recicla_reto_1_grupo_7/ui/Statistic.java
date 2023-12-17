package com.hyancy.eco_recicla_reto_1_grupo_7.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.CategoriesModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.UserViewModel;
import com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel.WasteViewModel;

import java.util.ArrayList;

public class Statistic extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView categoriasBottomBar, estadisticasBottomBar, consejosBottomBar, homeAppBottomBar, logoutBottomBar;
    private ImageView imageViewToolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private UserViewModel userViewModel;
    WasteViewModel wasteViewModel;
    private LineChart mLineChart;
    private HorizontalBarChart mHorizontalBarChar;
    private PieChart mPieChart;
    private BarChart mVerticalBarChart;
    private CategoriesModel categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_statistics_menu);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        wasteViewModel = new ViewModelProvider(this).get(WasteViewModel.class);
        categories = new CategoriesModel();

        setToolbar();
        initComponents();
        listenersMenuAppBar();
        setLineChart();
        setHorizontalBarChart();
        setPieChart();
        setVerticalBarChart();
    }

    private void setVerticalBarChart() {
        mVerticalBarChart.setDrawBarShadow(false);
        mVerticalBarChart.setDrawValueAboveBar(true);
        mVerticalBarChart.setMaxVisibleValueCount(50);
        mVerticalBarChart.setPinchZoom(false);
        mVerticalBarChart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 40.0f));
        barEntries.add(new BarEntry(2, 22.0f));
        barEntries.add(new BarEntry(3, 17.0f));
        barEntries.add(new BarEntry(4, 28.0f));
        barEntries.add(new BarEntry(5, 2.0f));
        barEntries.add(new BarEntry(6, 16.0f));
        barEntries.add(new BarEntry(7, 21.0f));
        barEntries.add(new BarEntry(8, 8.0f));
        barEntries.add(new BarEntry(9, 56.0f));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Data Set 1");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.9f);

        mVerticalBarChart.setData(data);
    }

    private void setPieChart() {
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        mPieChart.setDragDecelerationFrictionCoef(1.0f);

        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);
        mPieChart.setTransparentCircleRadius(20.0f);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(34.0f, categories.getListCategories().get(1)));
        yValues.add(new PieEntry(23.0f, categories.getListCategories().get(2)));
        yValues.add(new PieEntry(14.3f, categories.getListCategories().get(3)));
        yValues.add(new PieEntry(35.5f, categories.getListCategories().get(4)));
        yValues.add(new PieEntry(40.8f, categories.getListCategories().get(5)));
        yValues.add(new PieEntry(23.7f, categories.getListCategories().get(6)));
        yValues.add(new PieEntry(23.7f, categories.getListCategories().get(7)));
        yValues.add(new PieEntry(23.7f, categories.getListCategories().get(8)));
        yValues.add(new PieEntry(23.7f, categories.getListCategories().get(9)));

        PieDataSet dataSet = new PieDataSet(yValues, "Categorías");
        dataSet.setSliceSpace(3.0f);
        dataSet.setSelectionShift(15.0f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10.0f);
        data.setValueTextColor(Color.YELLOW);

        mPieChart.setData(data);
        mPieChart.setBackgroundColor(getColor(R.color.green_app_secondary));
    }

    private void setHorizontalBarChart() {
        BarData data = new BarData(getDataSet().get(0));
        data.setValueTextSize(14.0f);
        mHorizontalBarChar.setData(data);
        Description desc = new Description();
        desc.setText("Category de residuos");
        desc.setTextSize(14);
        mHorizontalBarChar.setDescription(desc);
        mHorizontalBarChar.animateXY(2500, 2500);
        mHorizontalBarChar.setFitBars(true);
        mHorizontalBarChar.getXAxis().setLabelCount(9);
        mHorizontalBarChar.setExtraRightOffset(5.0f);
        mHorizontalBarChar.setScaleEnabled(true);

        mHorizontalBarChar.invalidate();

        Legend legend = mHorizontalBarChar.getLegend();
        legend.setEnabled(true);
        XAxis xAxis = mHorizontalBarChar.getXAxis();
        xAxis.setDrawGridLines(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1);
        xAxis.setDrawLabels(true);
        xAxis.setXOffset(10);
        xAxis.setDrawAxisLine(true);

        mHorizontalBarChar.getXAxis().setValueFormatter(new IndexAxisValueFormatter(categories.getListCategories().subList(1, 10)));

    }

    private void setLineChart() {

        mLineChart.setDragDecelerationEnabled(true);
        mLineChart.setScaleEnabled(true);

        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<Entry> zValues = new ArrayList<>();
        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 20f));
        yValues.add(new Entry(2, 10f));
        yValues.add(new Entry(3, 55f));
        yValues.add(new Entry(4, 62f));
        yValues.add(new Entry(5, 70f));
        yValues.add(new Entry(6, 80f));
        yValues.add(new Entry(7, 10f));
        yValues.add(new Entry(8, 80f));
        yValues.add(new Entry(9, 23f));
        yValues.add(new Entry(10, 48f));
        yValues.add(new Entry(11, 0f));
        yValues.add(new Entry(12, 17f));

        zValues.add(new Entry(0, 60f));
        zValues.add(new Entry(1, 20f));
        zValues.add(new Entry(2, 10f));
        zValues.add(new Entry(3, 55f));
        zValues.add(new Entry(4, 72f));
        zValues.add(new Entry(5, 70f));
        zValues.add(new Entry(6, 80f));
        zValues.add(new Entry(7, 0f));
        zValues.add(new Entry(8, 80f));
        zValues.add(new Entry(9, 50f));
        zValues.add(new Entry(10, 80f));
        zValues.add(new Entry(11, 90f));
        zValues.add(new Entry(12, 80f));

        LineDataSet set1 = new LineDataSet(yValues, "Data Set 1");
        LineDataSet set2 = new LineDataSet(zValues, "Data Set 2");

        set1.setFillAlpha(110);
        set1.setColor(getColor(R.color.green_app));
        set1.setCircleColor(getColor(R.color.beige_app));
        set1.setValueTextSize(10.0F);
        set1.setLineWidth(2.0f);

        set2.setFillAlpha(110);
        set2.setColor(Color.RED);
        set2.setCircleColor(getColor(R.color.beige_app));
        set2.setValueTextSize(10.0F);
        set2.setLineWidth(2.0f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);

        LineData data = new LineData(dataSets);

        mLineChart.setData(data);
    }

    private void initComponents() {
        homeAppBottomBar = findViewById(R.id.home_menu_bottom_bar);
        categoriasBottomBar = findViewById(R.id.categorias_menu_bottom_bar);
        estadisticasBottomBar = findViewById(R.id.estadisticas_menu_bottom_bar);
        consejosBottomBar = findViewById(R.id.consejos_menu_bottom_bar);
        logoutBottomBar = findViewById(R.id.logout_menu_bottom_bar);
        imageViewToolbar = findViewById(R.id.menu_hamburguesa);

        mLineChart = findViewById(R.id.line_chart);
        mHorizontalBarChar = findViewById(R.id.horizontal_bar_chart);
        mPieChart = findViewById(R.id.pie_chart);
        mVerticalBarChart = findViewById(R.id.vertical_bar_chart);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.menu_navigation);

        navigationView.setNavigationItemSelectedListener(this);
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
            drawerLayout.openDrawer(GravityCompat.END);
        });
    }


    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentCategorias = new Intent(Statistic.this, Category.class);
        Intent intentEstadisticas = new Intent(Statistic.this, Statistic.class);
        Intent intentConsejos = new Intent(Statistic.this, Tips.class);
        Intent intentLogout = new Intent(Statistic.this, Index.class);
        Intent intentPrincipal = new Intent(Statistic.this, Main.class);
        Intent intentInfoApp = new Intent(Statistic.this, AppInformation.class);

        listaIntents.add(intentCategorias);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);
        listaIntents.add(intentPrincipal);
        listaIntents.add(intentInfoApp);

        return listaIntents;
    }

    private void logoutCurrentSesion() {
        userViewModel.logoutSesion();
        Intent intentLogout = new Intent(getApplicationContext(), Index.class);
        startActivity(intentLogout);
        finish();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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
            drawerLayout.closeDrawer(GravityCompat.END);
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

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("ENE");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("ABR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        xAxis.add("JUL");
        xAxis.add("AGO");
        xAxis.add("SEP");
        xAxis.add("OCT");
        xAxis.add("NOV");
        xAxis.add("DIC");

        return xAxis;
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        String idUser = userViewModel.getUidUser();

        Query query = wasteViewModel.getQuantityWasteByUser(idUser);
        int i = 0;
        System.out.println(query.count());
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                float quantity = 0.0f;
                for(QueryDocumentSnapshot val : value){
                    quantity = Float.parseFloat(val.get("quantity").toString());
                    barEntries.add(new BarEntry(i +1, quantity));
                    System.out.println(quantity);
                }
            }
        });
        barEntries.add(new BarEntry(0, 42.5f));
        barEntries.add(new BarEntry(1, 52.5f));
        barEntries.add(new BarEntry(2, 34.5f));
        barEntries.add(new BarEntry(3, 65f));
        barEntries.add(new BarEntry(4, 12f));
        barEntries.add(new BarEntry(5, 28f));
        barEntries.add(new BarEntry(6, 69f));
        barEntries.add(new BarEntry(7, 100f));
        barEntries.add(new BarEntry(8, 88f));

        BarDataSet barDataSet1 = new BarDataSet(barEntries, "");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets.add(barDataSet1);

        return dataSets;
    }

}