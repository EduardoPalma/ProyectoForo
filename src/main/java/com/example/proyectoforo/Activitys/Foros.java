package com.example.proyectoforo.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.proyectoforo.R;
import com.example.proyectoforo.clases.Foro;
import com.example.proyectoforo.estructuras.Arbol;
import com.example.proyectoforo.estructuras.NodoArbol;

import java.util.ArrayList;

public class Foros extends AppCompatActivity {
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
        llenarForos(arbol.getRaiz());
        Adapter adapter = new Adapter(foros);
        recycler.setAdapter(adapter);
    }

    private void llenarForos(NodoArbol raiz){
        if(raiz != null){
            llenarForos(raiz.getHijoIzquierdo());
            foros.add(raiz.getForo());
            llenarForos(raiz.getHijoDerecho());
        }
    }
}