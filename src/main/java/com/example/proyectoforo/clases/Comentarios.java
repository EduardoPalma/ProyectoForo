package com.example.proyectoforo.clases;

import java.io.Serializable;

public class Comentarios implements Serializable {
    private String texto;
    private Usuario usuario;

    public Comentarios(String texto,Usuario usuario){
        this.texto = texto;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
