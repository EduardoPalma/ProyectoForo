package com.example.proyectoforo.estructuras;

import com.example.proyectoforo.clases.Usuario;

import java.io.Serializable;

public class ListaUsuario implements Serializable {
    private NodoUsuario primero;
    private NodoUsuario ultimo;

    public ListaUsuario(){
        this.ultimo = null;
        this.primero = null;
    }

    public boolean ingresarUsuario(Usuario usuario){
        NodoUsuario nuevo = new NodoUsuario(usuario);
        if(this.primero == null){
            this.primero = nuevo;
            this.ultimo = nuevo;
        }else{
            nuevo.setPrev(this.ultimo);
            this.ultimo.setNext(nuevo);
            this.ultimo = nuevo;
        }
        return true;
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        NodoUsuario aux = this.primero;
        while (aux != null) {
            if (aux.getUsuario().getNombreUsuario().equals(nombreUsuario)) {
                return aux.getUsuario();
            }
            aux = aux.getNext();
        }
        return null;
    }
    public boolean eliminar(String nombreUsuario){

        if(this.primero.getUsuario().getNombreUsuario().equalsIgnoreCase(nombreUsuario)){
            NodoUsuario aux = this.primero;
            this.primero.getNext().setPrev(null);
            this.primero = this.primero.getNext();
            aux.setNext(null);
            return true;
        }else{
            if(this.ultimo.getUsuario().getNombreUsuario().equalsIgnoreCase(nombreUsuario)){
                NodoUsuario aux = this.ultimo;
                this.ultimo.getPrev().setNext(null);
                this.ultimo = this.ultimo.getPrev();
                aux.setPrev(null);
                return true;
            }else{
                NodoUsuario aux = this.primero.getNext();
                while(aux != this.ultimo){
                    if(aux.getUsuario().getNombreUsuario().equalsIgnoreCase(nombreUsuario)){
                        break;
                    }
                    aux = aux.getNext();
                }
                if(aux == this.ultimo) return false;
                aux.getPrev().setNext(aux.getNext());
                aux.getNext().setPrev(aux.getPrev());
                aux.setNext(null);
                aux.setPrev(null);
                return true;
            }
        }
    }

    public NodoUsuario getPrimero() {
        return primero;
    }
}
