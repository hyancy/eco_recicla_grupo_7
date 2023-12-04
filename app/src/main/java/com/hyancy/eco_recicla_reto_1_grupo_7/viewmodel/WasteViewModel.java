package com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel;

import androidx.lifecycle.ViewModel;

import com.hyancy.eco_recicla_reto_1_grupo_7.domain.WasteUseCase;

import java.util.Date;

public class WasteViewModel extends ViewModel {
    private WasteUseCase wasteUseCase = new WasteUseCase();

    public void createWaste(String description, String photoUrl, Date registerDate, String location, String category, double quantity, int points) {
        wasteUseCase.setWaste(description, photoUrl, registerDate, location, category, quantity, points);
    }
}
