package com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel;

import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hyancy.eco_recicla_reto_1_grupo_7.domain.WasteUseCase;

import java.util.ArrayList;

public class WasteViewModel extends ViewModel {
    private WasteUseCase wasteUseCase = new WasteUseCase();

    private ArrayList<QueryDocumentSnapshot> wasteList = new ArrayList<>();


    public void createWaste(String description, String photoUrl, String registerDate, String location, String category, double quantity, int points) {
        wasteUseCase.setWaste(description, photoUrl, registerDate, location, category, quantity, points);
    }

    public void getWasteByUserId(String idCurrentUser, ArrayList<QueryDocumentSnapshot> wasteList, TextView tvAccumulatedAmount, TextView tvAccumulatedPoints, String category) {
        wasteUseCase.getWasteByUserId(idCurrentUser, wasteList, tvAccumulatedAmount, tvAccumulatedPoints, category);
    }

}
