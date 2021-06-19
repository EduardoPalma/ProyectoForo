package com.example.proyectoforo.Activitys;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyectoforo.R;
import com.example.proyectoforo.clases.Foro;
import com.example.proyectoforo.clases.Usuario;
import com.example.proyectoforo.estructuras.Arbol;
import com.example.proyectoforo.estructuras.TablaHash;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button inicio,registro;
    private TablaHash tablaUsuarios;
    private Arbol arbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arbol = new Arbol();
        tablaUsuarios = new TablaHash(200);
        Usuario u = new Usuario("eduardo","1234","hellopalma");
        tablaUsuarios.ingresar(u);
        Foro f1 = new Foro("prueba1","descripcion 1 de prueba","LA PRUEBA 1",u);
        f1.getLc().setCant(10);
        Foro f2 = new Foro("prueba2","descripcion 2 de prueba","LA PRUEBA 2",u);
        f2.getLc().setCant(3);
        Foro f3 = new Foro("prueba3","descripcion 3 de prueba","LA PRUEBA 3",u);
        f3.getLc().setCant(7);
        arbol.insertar(f1);
        arbol.insertar(f2);
        arbol.insertar(f3);
        inicio = (Button) findViewById(R.id.iniciar);
        registro = (Button) findViewById(R.id.registro);
        inicio.setOnClickListener(this);
        registro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(inicio.getId() == v.getId()){
            Intent inicioSesion = new Intent(this,IniciarSesion.class);
            inicioSesion.putExtra("tabla",tablaUsuarios);
            inicioSesion.putExtra("ar",arbol);
            startActivity(inicioSesion);
        }
        if(registro.getId() == v.getId()){
            Intent registro = new Intent(this,Registro.class);
            startActivityForResult(registro,2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){

        }else{
            if(requestCode == 2){
                if(resultCode == Activity.RESULT_OK){
                    Usuario u = (Usuario) data.getExtras().getSerializable("usuario");
                    this.tablaUsuarios.ingresar(u);
                    Toast.makeText(getApplicationContext(),"ingreso",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}