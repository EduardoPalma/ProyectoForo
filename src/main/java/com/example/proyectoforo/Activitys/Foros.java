package com.example.proyectoforo.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.proyectoforo.R;
import com.example.proyectoforo.clases.Foro;
import com.example.proyectoforo.clases.Usuario;
import com.example.proyectoforo.estructuras.Arbol;
import com.example.proyectoforo.estructuras.ListaComentario;
import com.example.proyectoforo.estructuras.NodoArbol;

import java.util.ArrayList;

public class Foros extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Foro> foros;
    private RecyclerView recycler;
    private Arbol arbol;
    private Usuario u;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foros);
        foros = new ArrayList<>();
        recycler = (RecyclerView) findViewById(R.id.recicle);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        u = (Usuario) getIntent().getExtras().getSerializable("usuario");
        arbol = (Arbol) getIntent().getExtras().getSerializable("arbol");
        llenarForos(arbol.getRaiz());
        adapter = new Adapter(foros);
        adapter.setOnClickListener(this);
        recycler.setAdapter(adapter);
    }

    private void llenarForos(NodoArbol raiz){
        if(raiz != null){
            this.foros.add(raiz.getForo());
            menorMayor(raiz.getHijoIzquierdo());
            menorMayor(raiz.getHijoDerecho());
        }
    }

    private void menorMayor(NodoArbol raiz){
        if(raiz != null){
            menorMayor(raiz.getHijoIzquierdo());
            this.foros.add(raiz.getForo());
            menorMayor(raiz.getHijoDerecho());
        }
    }

    private void mayorMenor(NodoArbol raiz){
        if(raiz != null){
            mayorMenor(raiz.getHijoDerecho());
            this.foros.add(raiz.getForo());
            mayorMenor(raiz.getHijoIzquierdo());
        }
    }

    private void actualizar(int cantidad,ListaComentario lc){
        for(int i=0;i<this.foros.size();i++){
            if(this.foros.get(i).getLc().getCant() == cantidad){
                this.foros.get(i).setLc(lc);
            }
        }
    }

    private void actualizarArbol(Arbol arbol){
        for(int i=0;i<this.foros.size();i++){
            arbol.insertar(this.foros.get(i));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_foro,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.crearforo){
            Intent intent = new Intent(this,CrearForo.class);
            intent.putExtra("usuario",this.u);
            startActivityForResult(intent,1);
        }else{
            if(item.getItemId() == R.id.cerrarSesion){
                Intent inten = new Intent(this,IniciarSesion.class);
                startActivityForResult(inten,2);
                finish();
            }else{
                if(item.getItemId() == R.id.orMaMe){
                    this.foros.clear();
                    adapter.notifyDataSetChanged();
                    mayorMenor(this.arbol.getRaiz());
                }else{
                    if(item.getItemId() == R.id.orMeMa){
                        this.foros.clear();
                        adapter.notifyDataSetChanged();
                        menorMayor(this.arbol.getRaiz());
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                Foro f = (Foro) data.getExtras().getSerializable("foro");
                this.foros.clear();
                adapter.notifyDataSetChanged();
                this.arbol.insertar(f);
                llenarForos(this.arbol.getRaiz());
            }
        }else{
            if(requestCode == 3){
                if(resultCode == Activity.RESULT_OK){
                    int cantidad = data.getExtras().getInt("cantidad");
                    ListaComentario list = (ListaComentario) data.getExtras().getSerializable("lista");
                    actualizar(cantidad,list);
                    this.arbol.vaciar();
                    actualizarArbol(this.arbol);
                    this.foros.clear();
                    adapter.notifyDataSetChanged();
                    llenarForos(this.arbol.getRaiz());
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intentComentarios = new Intent(this,ActivityComenForo.class);
        intentComentarios.putExtra("listaComentarios",this.foros.get(this.recycler.getChildAdapterPosition(v)).getLc());
        intentComentarios.putExtra("titulo",this.foros.get(this.recycler.getChildAdapterPosition(v)).getTitulo());
        intentComentarios.putExtra("descripcion",this.foros.get(this.recycler.getChildAdapterPosition(v)).getDescrip());
        intentComentarios.putExtra("usuarioForo",this.foros.get(this.recycler.getChildAdapterPosition(v)).getU().getNombreUsuario());
        intentComentarios.putExtra("usuario",u);

        startActivityForResult(intentComentarios,3);
    }
}