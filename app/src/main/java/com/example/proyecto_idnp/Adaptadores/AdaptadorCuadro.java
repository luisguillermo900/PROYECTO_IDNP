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

import com.example.proyecto_idnp.R;

import java.util.List;

public class AdaptadorCuadro extends RecyclerView.Adapter<AdaptadorCuadro.ViewHolder>{
    private List<Cuadro> listaCuadros;
    private Context contexto;
    private OnCuadroClickListener listener;

    public AdaptadorCuadro(List<Cuadro> listaCuadros, Context contexto, OnCuadroClickListener listener) {
        this.listaCuadros = listaCuadros;
        this.contexto = contexto;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_cuadros, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTituloCuadro.setText(listaCuadros.get(position).getNombreCuadro());
        Log.d(TAG,"onBindViewHolder" + listaCuadros.get(position).getNombreCuadro());
        holder.txtDescripcionCuadro.setText(listaCuadros.get(position).getDescripcionCuadro());
        Log.d(TAG,"onBindViewHolder" + listaCuadros.get(position).getDescripcionCuadro());
        Glide.with(contexto)
                .load(listaCuadros.get(position).getFotoCuadro())
                .centerCrop()
                .into(holder.imgFotoCuadro);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    Log.d(TAG,"Se pulsó sobre item" + holder.getAdapterPosition());
                    listener.onCuadroClick(listaCuadros.get(holder.getAdapterPosition()));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaCuadros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFotoCuadro;
        private TextView txtTituloCuadro;
        private TextView txtDescripcionCuadro;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgFotoCuadro = itemView.findViewById(R.id.imgFotoCuadro);
            txtTituloCuadro = itemView.findViewById(R.id.txtTituloCuadro);
            txtDescripcionCuadro = itemView.findViewById(R.id.txtDescripcionCuadro);
        }
    }
}