package com.hyancy.eco_recicla_reto_1_grupo_7.domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.repository.FirebaseRepo;

public class DataUseCase {
    private FirebaseRepo repository = new FirebaseRepo();
    public FirebaseFirestore getData() {
        return repository.getDb();
    }

    public FirebaseAuth getAuth(){
        return repository.getmAuth();
    }

    public FirebaseUser getCurrentUser(){
        return repository.getCurrentUser();
    }
}
