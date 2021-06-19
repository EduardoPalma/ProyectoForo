package com.example.proyectoforo.estructuras;

import com.example.proyectoforo.clases.Comentarios;

import java.io.Serializable;

public class ListaComentario implements Serializable {
    private NodoLista primero;
    private NodoLista ultimo;
    private int cant;

    public ListaComentario(){
        this.primero = null;
        this.ultimo = null;
        this.cant = 0;
    }

    public boolean ingresarComentario(Comentarios comentario){
        NodoLista nuevo = new NodoLista(comentario);
        if(this.primero == null){
            this.primero = nuevo;
            this.ultimo = nuevo;
            this.cant = 1;
            return true;
        }else{
            nuevo.setPrev(this.ultimo);
            this.ultimo.setNext(nuevo);
            this.ultimo = nuevo;
            this.cant++;
            return true;
        }
    }

    public Comentarios buscarComentario(String comentario){
        NodoLista aux = this.primero;
        while(aux != null){
            if(aux.getComentario().getTexto().equalsIgnoreCase(comentario)){
                return aux.getComentario();
            }
            aux = aux.getNext();
        }
        return null;
    }


    public NodoLista getPrimero() {
        return primero;
    }

    public NodoLista getUltimo() {
        return ultimo;
    }

    public void setPrimero(NodoLista primero) {
        this.primero = primero;
    }

    public void setUltimo(NodoLista ultimo) {
        this.ultimo = ultimo;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}
