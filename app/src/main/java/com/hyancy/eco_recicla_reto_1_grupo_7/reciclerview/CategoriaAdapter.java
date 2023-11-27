package com.hyancy.eco_recicla_reto_1_grupo_7.reciclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyancy.eco_recicla_reto_1_grupo_7.R;

import java.util.ArrayList;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaViewHolder> {
    ArrayList<ModeloCategoria> listaCategorias;
    View view;

    public CategoriaAdapter(ArrayList<ModeloCategoria> listaCategorias) {
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
}
