package com.hyancy.eco_recicla_reto_1_grupo_7.domain;

import com.hyancy.eco_recicla_reto_1_grupo_7.data.ProductDataSet;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.repository.FirebaseRepo;
import com.hyancy.eco_recicla_reto_1_grupo_7.ui.models.WasteModel;

import java.util.ArrayList;
import java.util.Date;

public class WasteUseCase {
    private final ProductDataSet productDataSet = new ProductDataSet();
    private FirebaseRepo repository = new FirebaseRepo();


    public ArrayList<WasteModel> getListProducts() {
        return productDataSet.createProductList();
    }

    public void setWaste(String description, String photoUrl, Date registerDate, String location, String category, double quantity, int points) {
        repository.setWasteData(description, photoUrl, registerDate, location, category, quantity, points);
    }
}
