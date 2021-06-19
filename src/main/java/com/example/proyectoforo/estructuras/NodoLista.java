package com.example.proyectoforo.estructuras;

import com.example.proyectoforo.clases.Comentarios;

import java.io.Serializable;

public class NodoLista implements Serializable {
    private NodoLista next;
    private NodoLista prev;
    private Comentarios comentario;

    public NodoLista(Comentarios comentario){
        this.comentario = comentario;
        this.next = null;
        this.prev = null;
    }

    public Comentarios getComentario(){
        return this.comentario;
    }

    public NodoLista getNext() {
        return next;
    }

    public NodoLista getPrev() {
        return prev;
    }
    
    public void setNext(NodoLista next){
        this.next = next;
    }

    public void setPrev(NodoLista prev) {
        this.prev = prev;
    }
}
