package com.example.proyecto_idnp.Adaptadores;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyecto_idnp.Entidades.ResultadoFiltro;
import com.example.proyecto_idnp.R;

import java.util.List;

public class AdaptadorResultado extends RecyclerView.Adapter<AdaptadorResultado.ViewHolder> {
    private List<ResultadoFiltro> resultados;
    private Context contexto;
    private OnResultadoClickListener listener;

    public AdaptadorResultado(List<ResultadoFiltro> resultados, Context contexto, OnResultadoClickListener listener) {
        this.resultados = resultados;
        this.contexto = contexto;
        this.listener = listener;
    }
    @NonNull
    @Override
    public AdaptadorResultado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_resultados, parent, false);
        return new AdaptadorResultado.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.txtTituloResultado.setText(resultados.get(position).getNombre());
        Glide.with(contexto)
                .load(resultados.get(position).getUrlImagen())
                .centerCrop()
                .into(holder.imgFotoResultado);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    Log.d(TAG,"Se pulsó sobre item" + holder.getAdapterPosition());
                    listener.onResultadoClick(resultados.get(holder.getAdapterPosition()));
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return resultados.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFotoResultado;
        private TextView txtTituloResultado;
        public ViewHolder(@NonNull View itemVIew){
            super(itemVIew);
            imgFotoResultado = itemVIew.findViewById(R.id.imgFotoResultado);
            txtTituloResultado = itemVIew.findViewById(R.id.txtTituloResultado);
        }
    }
}