package com.hyancy.eco_recicla_reto_1_grupo_7.ui.models;

public class CategoryModel {
    private String categoria;
    private int pathImageCategoria;

    public CategoryModel(String categoria, int pathImageCategoria) {
        this.categoria = categoria;
        this.pathImageCategoria = pathImageCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPathImageCategoria() {
        return pathImageCategoria;
    }

    public void setPathImageCategoria(int pathImageCategoria) {
        this.pathImageCategoria = pathImageCategoria;
    }
}
