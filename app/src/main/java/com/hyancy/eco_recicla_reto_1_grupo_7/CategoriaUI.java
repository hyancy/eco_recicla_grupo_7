package com.hyancy.eco_recicla_reto_1_grupo_7;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.hyancy.eco_recicla_reto_1_grupo_7.reciclerview.CategoriaAdapter;
import com.hyancy.eco_recicla_reto_1_grupo_7.reciclerview.ModeloCategoria;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;

public class CategoriaUI extends AppCompatActivity {
    RecyclerView recyclerViewCategoria;
    ArrayList<ModeloCategoria> listaCategorias = new ArrayList<>();
    ImageView estadisticasBottomBar, consejosBottomBar, infoAppBottomBar, logoutBottomBar;
    SearchView filtrarCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_ui);

        initComponents();
        initCategoriasUI();
        listenersButtons();
    }

    private void initComponents() {
        recyclerViewCategoria = findViewById(R.id.recycler_view_categorias);

        estadisticasBottomBar = findViewById(R.id.estadisticas_menu_bottom_bar);
        consejosBottomBar = findViewById(R.id.consejos_menu_bottom_bar);
        infoAppBottomBar = findViewById(R.id.info_app_menu_bottom_bar);
        logoutBottomBar = findViewById(R.id.logout_menu_bottom_bar);
    }

    private void listenersButtons() {
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


    private void initCategoriasUI() {

        generarListaCategorias("Aceites", R.drawable.imagen_categoria_aceites_categorias);
        generarListaCategorias("Baterias /\nPilas", R.drawable.imagen_categoria_baterias_categorias);
        generarListaCategorias("Maderas /\nEscombros", R.drawable.imagen_categoria_madera_escombros_categorias);
        generarListaCategorias("Metales", R.drawable.imagen_categoria_metal_chatarra_categorias);
        generarListaCategorias("Papel /\nCartón", R.drawable.imagen_categoria_palpel_carton_categorias);
        generarListaCategorias("Plásticos", R.drawable.imagen_categoria_plastico_categorias);
        generarListaCategorias("Tetrabrik", R.drawable.imagen_categoria_tetrabrick_categorias);
        generarListaCategorias("Vidrios", R.drawable.imagen_categoria_vidrio_categorias);
        generarListaCategorias("Orgánicos", R.drawable.imagen_categoria_organicos_categorias);


        if (getRotation(getApplicationContext()).equals("horizontal")) {
            if (listaCategorias.size() > 0) {
                CategoriaAdapter adapterCategorias = new CategoriaAdapter(listaCategorias);
                recyclerViewCategoria.setLayoutManager(new GridLayoutManager(this, 4));
                recyclerViewCategoria.setHasFixedSize(true);
                recyclerViewCategoria.setAdapter(adapterCategorias);
            } else {
                System.out.println("Error al renderizar categorías");
            }
        } else {
            if (listaCategorias.size() > 0) {
                CategoriaAdapter adapterCategorias = new CategoriaAdapter(listaCategorias);
                recyclerViewCategoria.setLayoutManager(new GridLayoutManager(this, 3));
                recyclerViewCategoria.setHasFixedSize(true);
                recyclerViewCategoria.setAdapter(adapterCategorias);
            } else {
                System.out.println("Error al renderizar categorías");
            }
        }
    }

    public String getRotation(Context context) {
        final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                return "vertical";
            case Surface.ROTATION_90:
            default:
                return "horizontal";
        }
    }

    public ModeloCategoria categoriaFactory(String tipoCategoria, int pathCategoria) {
        ModeloCategoria categoria = new ModeloCategoria(tipoCategoria, pathCategoria);
        return categoria;
    }

    public ArrayList<ModeloCategoria> generarListaCategorias(String tipoCategoria, int pathCategoria) {
        ModeloCategoria categoria = categoriaFactory(tipoCategoria, pathCategoria);
        listaCategorias.add(categoria);
        return listaCategorias;
    }

    private ArrayList<Intent> initIntents() {
        ArrayList<Intent> listaIntents = new ArrayList<>();
        Intent intentRegistroResiduos = new Intent(CategoriaUI.this, FormularioRegistroResiduo.class);
        Intent intentEstadisticas = new Intent(CategoriaUI.this, Statistic.class);
        Intent intentConsejos = new Intent(CategoriaUI.this, Consejos.class);
        Intent intentLogout= new Intent(CategoriaUI.this, MainActivity.class);

        listaIntents.add(intentRegistroResiduos);
        listaIntents.add(intentEstadisticas);
        listaIntents.add(intentConsejos);
        listaIntents.add(intentLogout);

        return listaIntents;
    }
}