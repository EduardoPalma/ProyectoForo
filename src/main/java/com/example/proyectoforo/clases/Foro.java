package com.example.proyectoforo.clases;

import com.example.proyectoforo.estructuras.ListaComentario;

import java.io.Serializable;

public class Foro implements Serializable {
    private String tema;
    private String descrip;
    private String titulo;
    private Usuario u;
    private ListaComentario lc;

    public Foro(String tema,String descrip,String titulo,Usuario u){
        this.tema = tema;
        this.descrip = descrip;
        this.titulo = titulo;
        this.lc = new ListaComentario();
        this.u = u;
    }

    public String getTema() {
        return tema;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ListaComentario getLc() {
        return lc;
    }

    public void setLc(ListaComentario lc) {
        this.lc = lc;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }
}
