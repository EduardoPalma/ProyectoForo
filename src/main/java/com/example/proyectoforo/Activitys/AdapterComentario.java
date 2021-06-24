package com.example.proyectoforo.Activitys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoforo.R;
import com.example.proyectoforo.clases.Comentarios;

import java.util.ArrayList;

public class AdapterComentario extends RecyclerView.Adapter<AdapterComentario.ViewHolderComentario>{

    private ArrayList<Comentarios> listComentarios;

    public AdapterComentario(ArrayList<Comentarios> comentarios) {
        this.listComentarios = comentarios;
    }

    @NonNull
    @Override
    public AdapterComentario.ViewHolderComentario onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contener_comentario,parent,false);
        return new ViewHolderComentario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComentario.ViewHolderComentario holder, int position) {
        holder.usuario.setText(listComentarios.get(position).getUsuario().getNombreUsuario());
        holder.descripcion.setText(listComentarios.get(position).getTexto());
    }

    @Override
    public int getItemCount() {
        return listComentarios.size();
    }

    public class ViewHolderComentario extends RecyclerView.ViewHolder{
        TextView usuario,descripcion;
        public ViewHolderComentario(@NonNull View itemView) {
            super(itemView);
            usuario = (TextView) itemView.findViewById(R.id.UsuarioComentario);
            descripcion = (TextView) itemView.findViewById(R.id.comentUsuario);
        }
    }
}
