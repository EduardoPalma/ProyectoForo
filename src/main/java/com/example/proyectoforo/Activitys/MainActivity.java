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
import com.example.proyectoforo.clases.Comentarios;
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
        inicio = findViewById(R.id.iniciar);
        registro = findViewById(R.id.registro);
        inicio.setOnClickListener(this);
        registro.setOnClickListener(this);
        cargarDatos();

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
            //arreglar cosas
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

    private void cargarDatos(){
        Usuario u1 = new Usuario("eduardo","1234","hellopalma@gmail.com");
        Usuario u2 = new Usuario("Fran431","1253","francisca@gmail.com");
        Usuario u3 = new Usuario("Juan781","1242","juanito5@gmail.com");
        tablaUsuarios.ingresar(u1);
        tablaUsuarios.ingresar(u2);
        tablaUsuarios.ingresar(u3);

        Foro f1 = new Foro("Modificaciones en Android","La duda que tengo es como resolver los manejos de activitys","Ayuda Manejo de Activitis!!",u1);
        Comentarios c11 = new Comentarios("puedes ser mas especifico",u3);
        Comentarios c12 = new Comentarios("como decirle a un activity que se diriga a otro",u1);
        Comentarios c13 = new Comentarios("ocupa los Intent para resolverlo ",u3);
        Comentarios c14 = new Comentarios("ahh vere la documentacion gracias",u1);
        f1.getLc().ingresarComentario(c11);
        f1.getLc().ingresarComentario(c12);
        f1.getLc().ingresarComentario(c13);
        f1.getLc().ingresarComentario(c14);
        Foro f2 = new Foro("primer foro","es una prueba para ver los foros","LA PRUEBA MISMISIMA PRUEBA!",u3);
        Comentarios c21 = new Comentarios("esto es mi primero foro :D",u3);
        Comentarios c22 = new Comentarios("Bienvenido a este hermoso Foro",u2);
        Comentarios c23 = new Comentarios("Gracias  por resivirme !!",u1);
        f2.getLc().ingresarComentario(c21);
        f2.getLc().ingresarComentario(c22);
        f2.getLc().ingresarComentario(c23);
        Foro f3 = new Foro("futbol","quien ganara el partido de hoy Chile o Paraguay","Ganaador Chile vs Uruguay",u2);
        Comentarios c31 = new Comentarios("es dificil decidir, no se ve un equipo bueno por ambos lados",u1);
        Comentarios c32 = new Comentarios("seguramente gane chile o empante llevan jugando bien los ultimos 3 partidos",u2);
        Comentarios c33 = new Comentarios("Naa seguramente empaten o Gane Paraguay jajajaj",u3);
        Comentarios c34 = new Comentarios("no creo Paraaguar siempre a tenido mala suerte para los partidos",u2);
        Comentarios c35 = new Comentarios("yo me voy por chile el equipo sigue siendo mejor",u1);
        Comentarios c36 = new Comentarios("seguramente sea asi suerte para el equipo",u3);
        f3.getLc().ingresarComentario(c31);
        f3.getLc().ingresarComentario(c32);
        f3.getLc().ingresarComentario(c33);
        f3.getLc().ingresarComentario(c34);
        f3.getLc().ingresarComentario(c35);
        f3.getLc().ingresarComentario(c36);


        arbol.insertar(f1);
        arbol.insertar(f2);
        arbol.insertar(f3);
    }
}