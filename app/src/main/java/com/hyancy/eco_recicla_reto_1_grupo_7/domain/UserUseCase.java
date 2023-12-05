package com.hyancy.eco_recicla_reto_1_grupo_7.domain;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.repository.FirebaseRepo;

import java.util.Date;

public class UserUseCase {
    private FirebaseRepo repository = new FirebaseRepo();

    public void setUser(String name, Integer age, String email, String password) {
        repository.setUserData(name, age, email, password);
    }

}
