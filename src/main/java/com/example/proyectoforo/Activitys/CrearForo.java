package com.example.proyectoforo.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyectoforo.R;
import com.example.proyectoforo.clases.Foro;
import com.example.proyectoforo.clases.Usuario;

public class CrearForo extends AppCompatActivity implements View.OnClickListener {
    private EditText tituloForo,temaforo,desForo;
    private Button botonCrerForo;
    private Usuario u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_foro);
        u = (Usuario) getIntent().getExtras().getSerializable("usuario");
        tituloForo = findViewById(R.id.foroTitulo);
        temaforo = findViewById(R.id.temaForo);
        desForo = findViewById(R.id.descripcionForo);
        botonCrerForo = findViewById(R.id.buttonCrerForo);
        botonCrerForo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Foro f = new Foro(temaforo.getText().toString(),desForo.getText().toString(),tituloForo.getText().toString(),u);
        Intent intent = new Intent(this,Foros.class);
        intent.putExtra("foro",f);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}