package com.example.proyectoforo.estructuras;

import com.example.proyectoforo.clases.Usuario;

import java.io.Serializable;

public class NodoUsuario implements Serializable {
    private Usuario usuario;
    private NodoUsuario next;
    private NodoUsuario prev;

    public NodoUsuario(Usuario usuario){
        this.usuario = usuario;
        this.next = null;
        this.prev = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public NodoUsuario getNext() {
        return next;
    }

    public void setNext(NodoUsuario next) {
        this.next = next;
    }

    public NodoUsuario getPrev() {
        return prev;
    }

    public void setPrev(NodoUsuario prev) {
        this.prev = prev;
    }
}
