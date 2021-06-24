package com.example.proyectoforo.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.proyectoforo.R;
import com.example.proyectoforo.clases.Foro;
import com.example.proyectoforo.estructuras.Arbol;
import com.example.proyectoforo.estructuras.NodoArbol;

import java.util.ArrayList;

public class Foros extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Foro> foros;
    private RecyclerView recycler;
    private Arbol arbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foros);
        foros = new ArrayList<>();
        recycler = (RecyclerView) findViewById(R.id.recicle);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        arbol = (Arbol) getIntent().getExtras().getSerializable("arbol");
        LinearLayoutManager linear = new LinearLayoutManager(this);
        recycler.setLayoutManager(linear);
        llenarForos(arbol.getRaiz());
        Adapter adapter = new Adapter(foros);
        adapter.setOnClickListener(this);
        recycler.setAdapter(adapter);
    }

    private void llenarForos(NodoArbol raiz){
        if(raiz != null){
            llenarForos(raiz.getHijoIzquierdo());
            foros.add(raiz.getForo());
            llenarForos(raiz.getHijoDerecho());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_foro,menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intentComentarios = new Intent(this,ActivityComenForo.class);
        intentComentarios.putExtra("listaComentarios",this.foros.get(this.recycler.getChildAdapterPosition(v)).getLc());
        intentComentarios.putExtra("titulo",this.foros.get(this.recycler.getChildAdapterPosition(v)).getTitulo());
        intentComentarios.putExtra("descripcion",this.foros.get(this.recycler.getChildAdapterPosition(v)).getDescrip());
        intentComentarios.putExtra("usuario",this.foros.get(this.recycler.getChildAdapterPosition(v)).getU().getNombreUsuario());

        startActivity(intentComentarios);
    }
}