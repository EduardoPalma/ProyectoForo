package com.example.proyectoforo.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoforo.R;
import com.example.proyectoforo.clases.Usuario;
import com.example.proyectoforo.estructuras.Arbol;
import com.example.proyectoforo.estructuras.TablaHash;

public class IniciarSesion extends AppCompatActivity implements View.OnClickListener {
    private TablaHash tablaUsuarios;
    private Arbol arbol;
    private Button inicioSesion;
    private EditText nombreUsuario,contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        tablaUsuarios = (TablaHash) getIntent().getSerializableExtra("tabla");
        arbol = (Arbol) getIntent().getSerializableExtra("ar");
        inicioSesion = (Button) findViewById(R.id.inicioI);
        nombreUsuario = (EditText) findViewById(R.id.editTextTextPersonNameI);
        contra = (EditText) findViewById(R.id.editTextTextPasswordI);
        inicioSesion.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == inicioSesion.getId()){
            int i = tablaUsuarios.hash(nombreUsuario.getText().toString());
            Toast.makeText(getApplicationContext(),""+i,Toast.LENGTH_SHORT).show();
            Usuario u = tablaUsuarios.buscar(nombreUsuario.getText().toString());
            if(u != null){
                if(u.getPw().equals(contra.getText().toString())){
                    Intent f = new Intent(this,Foros.class);
                    Bundle b = new Bundle();
                    b.putSerializable("arbol",this.arbol);
                    f.putExtras(b);
                    startActivity(f);
                }else Toast.makeText(getApplicationContext(),"contraseña incorrecta",Toast.LENGTH_SHORT).show();
            }else Toast.makeText(getApplicationContext(),"Usuario No encontrado",Toast.LENGTH_LONG).show();
        }
    }
}