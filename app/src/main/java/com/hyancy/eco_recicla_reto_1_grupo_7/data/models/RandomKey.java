package com.hyancy.eco_recicla_reto_1_grupo_7.data.models;

import java.util.UUID;

public class RandomKey {
    public String createRandomKey(){
        return UUID.randomUUID().toString().toUpperCase();
    }
}
