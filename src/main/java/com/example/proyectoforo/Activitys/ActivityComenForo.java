package com.example.proyectoforo.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoforo.R;
import com.example.proyectoforo.clases.Comentarios;
import com.example.proyectoforo.estructuras.ListaComentario;
import com.example.proyectoforo.estructuras.NodoLista;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActivityComenForo extends AppCompatActivity {
    private TextView TituloForo,DescripcionForo,UsuarioForo;
    private RecyclerView recycleComentarios;
    private ArrayList<Comentarios> comentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comen_foro);

        comentarios = new ArrayList<>();
        recycleComentarios = (RecyclerView) findViewById(R.id.recicleComentarios);
        TituloForo = (TextView) findViewById(R.id.TituloForo) ;
        DescripcionForo = (TextView) findViewById(R.id.Descripcion) ;;
        UsuarioForo = (TextView) findViewById(R.id.UsuarioForo) ;
        TituloForo.setText(getIntent().getStringExtra("titulo"));
        DescripcionForo.setText(getIntent().getStringExtra("descripcion"));
        UsuarioForo.setText(getIntent().getStringExtra("usuario"));

        LinearLayoutManager linear = new LinearLayoutManager(this);
        recycleComentarios.setLayoutManager(linear);
        ListaComentario list = (ListaComentario) getIntent().getExtras().getSerializable("listaComentarios");
        llenarComentarios(list);
        AdapterComentario adapter = new AdapterComentario(comentarios);
        recycleComentarios.setAdapter(adapter);

    }

    private void llenarComentarios(ListaComentario listaComentario){
        NodoLista aux = listaComentario.getPrimero();
        while(aux != null){
            comentarios.add(aux.getComentario());
            aux = aux.getNext();
        }
    }
}