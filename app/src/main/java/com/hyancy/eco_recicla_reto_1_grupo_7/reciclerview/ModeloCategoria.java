package com.hyancy.eco_recicla_reto_1_grupo_7.reciclerview;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ModeloCategoria {
    private String categoria;
    private int pathImageCategoria;

    public ModeloCategoria(String categoria, int pathImageCategoria) {
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
