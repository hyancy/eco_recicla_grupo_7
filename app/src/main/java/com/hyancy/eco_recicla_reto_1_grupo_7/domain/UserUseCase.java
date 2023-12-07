package com.hyancy.eco_recicla_reto_1_grupo_7.domain;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.repository.FirebaseRepo;

import java.util.Date;

public class UserUseCase {
    private FirebaseRepo repository = new FirebaseRepo();

    public void createUser(String name, Integer age, String email, String password, Context context) {
        repository.createUser(name, age, email, password, context);
    }


    public void getUserData(String idUser) {
        repository.getUserData(idUser);
    }

}
