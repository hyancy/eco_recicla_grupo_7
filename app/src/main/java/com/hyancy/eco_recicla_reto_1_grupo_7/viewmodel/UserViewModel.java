package com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel;

import androidx.lifecycle.ViewModel;

import com.hyancy.eco_recicla_reto_1_grupo_7.domain.UserUseCase;

public class UserViewModel extends ViewModel {
    private UserUseCase userUseCase = new UserUseCase();

    public void createUser(String name, Integer age, String email, String password) {
        userUseCase.setUser(name, age, email, password);
    }
}
