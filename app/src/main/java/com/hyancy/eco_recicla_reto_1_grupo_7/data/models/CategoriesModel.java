package com.hyancy.eco_recicla_reto_1_grupo_7.data.models;

import java.util.ArrayList;

public class CategoriesModel {
    private final ArrayList <String> listCategories = new ArrayList<>();

    public ArrayList<String> getListCategories() {
        listCategories.add("Seleccione una categoría");
        listCategories.add("Aceites");
        listCategories.add("Baterías / Pilas");
        listCategories.add("Maderas / Escombros");
        listCategories.add("Metales");
        listCategories.add("Papel / Cartón");
        listCategories.add("Plásticos");
        listCategories.add("Tetrabrik");
        listCategories.add("Vidrios");
        listCategories.add("Orgánicos");
        return listCategories;
    }
}
