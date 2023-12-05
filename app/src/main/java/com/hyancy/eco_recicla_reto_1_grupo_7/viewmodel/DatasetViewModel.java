package com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hyancy.eco_recicla_reto_1_grupo_7.domain.DataUseCase;

public class DatasetViewModel extends ViewModel {
    private DataUseCase dataUseCase = new DataUseCase();


    public FirebaseFirestore getData(){
        return dataUseCase.getData();
    }

    public FirebaseAuth getmAuth(){
        return dataUseCase.getAuth();
    }

    public FirebaseUser getCurrentUser(){
        return dataUseCase.getCurrentUser();
    }
}
