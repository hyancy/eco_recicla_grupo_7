package com.hyancy.eco_recicla_reto_1_grupo_7.reciclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;

public class CategoriaViewHolder extends RecyclerView.ViewHolder {
    CardView cardCategoria;
    ImageView imagenCategoria;
    TextView tvTipoCategoria;

    public CategoriaViewHolder(@NonNull View itemView) {
        super(itemView);

        cardCategoria =itemView.findViewById(R.id.card_categoria);
        imagenCategoria = itemView.findViewById(R.id.image_view_categoria_card);
        tvTipoCategoria = itemView.findViewById(R.id.text_view_categoria_card);

    }

    public void render(ModeloCategoria modeloCategoria) {
        tvTipoCategoria.setText(String.valueOf(modeloCategoria.getCategoria()));
        imagenCategoria.setImageResource(modeloCategoria.getPathImageCategoria());
    }
}
