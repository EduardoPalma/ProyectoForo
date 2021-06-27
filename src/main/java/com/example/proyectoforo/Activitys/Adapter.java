package com.example.proyectoforo.Activitys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoforo.R;
import com.example.proyectoforo.clases.Foro;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderForo> implements View.OnClickListener{
    private ArrayList<Foro> foros;
    private View.OnClickListener listener;

    public Adapter(ArrayList<Foro> foros){
        this.foros = foros;
    }

    public void ingresarForo(Foro foro){
        this.foros.add(foro);
        notifyItemInserted(this.foros.size());
    }
    @NonNull
    @Override
    public ViewHolderForo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contenedor_foro,parent,false);
        view.setOnClickListener(this);
        return new ViewHolderForo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolderForo holder, int position) {
        holder.titulo.setText(foros.get(position).getTitulo());
        holder.descrip.setText(foros.get(position).getDescrip());
        holder.idUsuario.setText(foros.get(position).getU().getNombreUsuario());

    }

    @Override
    public int getItemCount() {
        return foros.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public class ViewHolderForo extends RecyclerView.ViewHolder{
        TextView titulo,descrip,idUsuario;

        public ViewHolderForo(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
            descrip = (TextView) itemView.findViewById(R.id.Descripcion);
            idUsuario = (TextView) itemView.findViewById(R.id.Usuario);

        }
    }
}
