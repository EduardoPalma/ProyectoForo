package com.example.proyectoforo.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoforo.R;
import com.example.proyectoforo.clases.Usuario;
import com.example.proyectoforo.estructuras.TablaHash;


public class Registro extends AppCompatActivity implements View.OnClickListener {
    private Button registrarse;
    private EditText nombreUsurio,contra,correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreUsurio = (EditText) findViewById(R.id.editTextTextPersonName);
        contra = (EditText) findViewById(R.id.editTextTextPassword);
        correo = (EditText) findViewById(R.id.editTextTextEmailAddress);

        registrarse = (Button) findViewById(R.id.registroAc);
        registrarse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == registrarse.getId()) {
            Usuario u = new Usuario(nombreUsurio.getText().toString(), contra.getText().toString(), correo.getText().toString());
            Intent refresh = new Intent(this,MainActivity.class);
            Bundle b = new Bundle();
            b.putSerializable("usuario",u);
            refresh.putExtras(b);
            setResult(Activity.RESULT_OK,refresh);
            finish();
        }
    }
}