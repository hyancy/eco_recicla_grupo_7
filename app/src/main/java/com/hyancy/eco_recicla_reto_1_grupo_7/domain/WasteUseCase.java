package com.hyancy.eco_recicla_reto_1_grupo_7.domain;

import android.widget.TextView;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.repository.FirebaseRepo;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.WasteModel;

import java.util.ArrayList;

public class WasteUseCase {
    private FirebaseRepo repository = new FirebaseRepo();

    public void setWaste(String description, String photoUrl, String registerDate, String location, String category, double quantity, int points) {
        repository.setWasteData(description, photoUrl, registerDate, location, category, quantity, points);
    }

    public void getWasteByUserId(String idCurrentUser, ArrayList<QueryDocumentSnapshot> wasteList, TextView tvAccumulatedAmount, TextView tvAccumulatedPoints, String category) {
        repository.getWasteByUserId(idCurrentUser, wasteList, tvAccumulatedAmount, tvAccumulatedPoints, category);
    }

}
