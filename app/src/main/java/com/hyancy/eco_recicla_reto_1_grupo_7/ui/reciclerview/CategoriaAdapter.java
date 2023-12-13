package com.hyancy.eco_recicla_reto_1_grupo_7.ui.reciclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.CategoryModel;

import java.util.ArrayList;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {
    ArrayList<CategoryModel> listaCategorias;
    View view;

    public CategoriaAdapter(ArrayList<CategoryModel> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categoria, parent, false);
        view = parent;
        return new CategoriaViewHolder(layoutInflater);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        holder.render(listaCategorias.get(position));
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }


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

        public void render(CategoryModel categoryModel) {
            tvTipoCategoria.setText(String.valueOf(categoryModel.getCategoria()));
            imagenCategoria.setImageResource(categoryModel.getPathImageCategoria());
        }
    }

}

