package com.hyancy.eco_recicla_reto_1_grupo_7.ui.models;

import java.util.ArrayList;

public class SpinnerModel{
    private final ArrayList <String> listSpinnerCategory = new ArrayList<>();

    public ArrayList<String> getListSpinnerCategory() {
        listSpinnerCategory.add("Seleccione una categoría");
        listSpinnerCategory.add("Aceites");
        listSpinnerCategory.add("Baterías / Pilas");
        listSpinnerCategory.add("Maderas / Escombros");
        listSpinnerCategory.add("Metales");
        listSpinnerCategory.add("Papel / Cartón");
        listSpinnerCategory.add("Plásticos");
        listSpinnerCategory.add("Tetrabrik");
        listSpinnerCategory.add("Vidrios");
        listSpinnerCategory.add("Orgánicos");
        return listSpinnerCategory;
    }
}
