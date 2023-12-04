package com.hyancy.eco_recicla_reto_1_grupo_7.data;

import com.hyancy.eco_recicla_reto_1_grupo_7.ui.models.WasteModel;

import java.util.ArrayList;
import java.util.Date;

public class ProductDataSet {
    public ArrayList<WasteModel> createProductList() {
        WasteModel producto = new WasteModel("xxx", "www",
                new Date("2/12/2023"), "Bta", "Aceite", 5.0, 10);
        WasteModel producto2 = new WasteModel( "xxx", "www",
                new Date("2/12/2023"), "Bta", "Papel", 5.0, 10);
        WasteModel producto3 = new WasteModel( "xxx", "www",
                new Date("2/12/2023"), "Bta", "Organicos", 5.0, 10);

        ArrayList productsList = new ArrayList();
        productsList.add(producto);
        productsList.add(producto2);
        productsList.add(producto3);

        return productsList;

    }
}
