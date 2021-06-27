package com.example.proyectoforo.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoforo.R;
import com.example.proyectoforo.clases.Comentarios;
import com.example.proyectoforo.clases.Usuario;
import com.example.proyectoforo.estructuras.ListaComentario;
import com.example.proyectoforo.estructuras.NodoLista;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActivityComenForo extends AppCompatActivity implements View.OnClickListener {
    private TextView TituloForo,DescripcionForo,UsuarioForo;
    private EditText comentarioUsuario;
    private Button enviarComentario;
    private RecyclerView recycleComentarios;
    private ArrayList<Comentarios> comentarios;
    private AdapterComentario adapter;
    private ListaComentario list;
    private int cantidadBaseComentarios;
    private Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comen_foro);

        comentarios = new ArrayList<>();
        recycleComentarios = (RecyclerView) findViewById(R.id.recicleComentarios);
        TituloForo = (TextView) findViewById(R.id.TituloForo);
        DescripcionForo = (TextView) findViewById(R.id.Descripcion);
        UsuarioForo = (TextView) findViewById(R.id.UsuarioForo);
        comentarioUsuario = (EditText) findViewById(R.id.mensaje);
        enviarComentario = (Button) findViewById(R.id.enviarComentario);
        enviarComentario.setOnClickListener(this);
        TituloForo.setText(getIntent().getStringExtra("titulo"));
        DescripcionForo.setText(getIntent().getStringExtra("descripcion"));
        UsuarioForo.setText(getIntent().getStringExtra("usuarioForo"));
        u = (Usuario) getIntent().getExtras().getSerializable("usuario");

        LinearLayoutManager linear = new LinearLayoutManager(this);
        recycleComentarios.setLayoutManager(linear);
        list = (ListaComentario) getIntent().getExtras().getSerializable("listaComentarios");
        cantidadBaseComentarios = list.getCant();
        llenarComentarios(list);
        adapter = new AdapterComentario(comentarios);
        recycleComentarios.setAdapter(adapter);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollBar();
            }
        });
    }

    private void llenarComentarios(ListaComentario listaComentario){
        NodoLista aux = listaComentario.getPrimero();
        while(aux != null){
            comentarios.add(aux.getComentario());
            aux = aux.getNext();
        }
    }

    @Override
    public void onClick(View v) {
        Comentarios c = new Comentarios(comentarioUsuario.getText().toString(),this.u);
        this.list.ingresarComentario(c);
        this.adapter.ingresarComentario(c);
        this.comentarioUsuario.setText("");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            Intent inte = new Intent(this,Foros.class);
            inte.putExtra("cantidad",this.cantidadBaseComentarios);
            inte.putExtra("lista",this.list);
            setResult(Activity.RESULT_OK,inte);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setScrollBar(){
        recycleComentarios.scrollToPosition(adapter.getItemCount()-1);
    }
}