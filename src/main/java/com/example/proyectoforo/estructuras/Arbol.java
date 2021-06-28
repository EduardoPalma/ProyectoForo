package com.example.proyectoforo.estructuras;

import com.example.proyectoforo.clases.Foro;

import java.io.Serializable;

public class Arbol implements Serializable {
    private NodoArbol raiz;
    private int cant;

    public Arbol(){
        this.raiz = null;
    }

    public int obtenerFe(NodoArbol nodo){
        if(nodo == null){
            return -1;
        }else return nodo.getFe();
    }

    public NodoArbol rotacionIzquierda(NodoArbol nodo){
        NodoArbol aux = nodo.getHijoIzquierdo();
        nodo.setHijoIzquierdo(aux.getHijoDerecho());
        aux.setHijoDerecho(nodo);
        nodo.setFe(Math.max(obtenerFe(nodo.getHijoIzquierdo()),obtenerFe(nodo.getHijoDerecho()))+1);
        aux.setFe(Math.max(obtenerFe(aux.getHijoIzquierdo()), obtenerFe(aux.getHijoDerecho()))+1);
        return aux;
    }

    public NodoArbol rotacionDerecha(NodoArbol nodo){
        NodoArbol aux = nodo.getHijoDerecho();
        nodo.setHijoDerecho(aux.getHijoIzquierdo());
        aux.setHijoIzquierdo(nodo);
        nodo.setFe(Math.max(obtenerFe(nodo.getHijoIzquierdo()), obtenerFe(nodo.getHijoDerecho()))+1);
        aux.setFe(Math.max(obtenerFe(aux.getHijoIzquierdo()),obtenerFe(aux.getHijoDerecho()))+1);
        return aux;
    }

    public NodoArbol rotacionDobleIzquierda(NodoArbol nodo){
        NodoArbol temp;
        nodo.setHijoIzquierdo(rotacionDerecha(nodo.getHijoIzquierdo()));
        temp = rotacionIzquierda(nodo);
        return temp;
    }

    public NodoArbol rotacionDobleDerecha(NodoArbol nodo){
        NodoArbol temp;
        nodo.setHijoDerecho(rotacionIzquierda(nodo.getHijoDerecho()));
        temp = rotacionDerecha(nodo);
        return temp;
    }

    public void insertar(Foro foro){
        NodoArbol nuevo = new NodoArbol(foro);
        if(this.raiz == null) this.raiz = nuevo;
        else raiz = insertarArbol(nuevo,this.raiz);
    }

    private NodoArbol insertarArbol(NodoArbol nuevo,NodoArbol raiz){
        NodoArbol nuevaRaiz = raiz;
        if(nuevo.getForo().getLc().getCant() < raiz.getForo().getLc().getCant()){
            if(raiz.getHijoIzquierdo() == null){
                raiz.setHijoIzquierdo(nuevo);
            }else{
                raiz.setHijoIzquierdo(insertarArbol(nuevo,raiz.getHijoIzquierdo()));
                if((obtenerFe(raiz.getHijoIzquierdo()) - obtenerFe(raiz.getHijoDerecho()) ) == 2){
                    if(nuevo.getForo().getLc().getCant() < raiz.getHijoIzquierdo().getForo().getLc().getCant()){
                        nuevaRaiz = rotacionDobleIzquierda(raiz);
                    }else nuevaRaiz = rotacionDobleIzquierda(raiz);
                }
            }
        }else{
            if(nuevo.getForo().getLc().getCant() >= raiz.getForo().getLc().getCant()){
                if(raiz.getHijoDerecho() == null){
                    raiz.setHijoDerecho(nuevo);
                }else{
                    raiz.setHijoDerecho(insertarArbol(nuevo, raiz.getHijoDerecho()));
                    if((obtenerFe(raiz.getHijoDerecho()) - obtenerFe(raiz.getHijoIzquierdo())) == 2){
                        if(nuevo.getForo().getLc().getCant() > raiz.getHijoDerecho().getForo().getLc().getCant()){
                            nuevaRaiz = rotacionDerecha(raiz);
                        }else nuevaRaiz = rotacionDobleDerecha(raiz);
                    }
                }
            }
        }

        //actualizar altura
        if((raiz.getHijoIzquierdo() == null) && (raiz.getHijoDerecho() != null)){
            raiz.setFe(raiz.getHijoDerecho().getFe()+1);
        }else if((raiz.getHijoDerecho() == null) && (raiz.getHijoIzquierdo() != null)){
            raiz.setFe(raiz.getHijoIzquierdo().getFe()+1);
        }else{
            raiz.setFe(Math.max(obtenerFe(raiz.getHijoIzquierdo()), obtenerFe(raiz.getHijoDerecho())) +1);
        }
        this.cant++;
        return nuevaRaiz;
    }
    public Foro buscarForo(int cant){
        return buscarForo(cant,this.raiz);
    }
    private Foro buscarForo(int cant,NodoArbol raiz){
        if(raiz == null) return null;
        else{
            if(raiz.getForo().getLc().getCant() == cant) return raiz.getForo();
            else{
                if(raiz.getForo().getLc().getCant() > cant) return buscarForo(cant,raiz.getHijoIzquierdo());
                else return buscarForo(cant,raiz.getHijoDerecho());
            }
        }

    }

    public void vaciar(){
        this.raiz = null;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}
