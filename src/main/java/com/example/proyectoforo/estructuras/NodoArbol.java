package com.example.proyectoforo.estructuras;

import com.example.proyectoforo.clases.Foro;

import java.io.Serializable;

public class NodoArbol implements Serializable {
    private Foro foro;
    private int fe;
    private NodoArbol hijoDerecho;
    private NodoArbol hijoIzquierdo;

    public NodoArbol(Foro foro){
        this.foro = foro;
        this.fe = 0;
        this.hijoDerecho = null;
        this.hijoIzquierdo = null;
    }

    public Foro getForo() {
        return foro;
    }

    public NodoArbol getHijoDerecho() {
        return hijoDerecho;
    }

    public NodoArbol getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public int getFe() {
        return fe;
    }

    public void setHijoDerecho(NodoArbol hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public void setHijoIzquierdo(NodoArbol hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }
}
