package com.example.proyectoforo.estructuras;


import com.example.proyectoforo.clases.Usuario;

import java.io.Serializable;

public class TablaHash implements Serializable {
    private ListaUsuario arregloUsuarios[];
    private int tam;

    public TablaHash(int tam){
        this.tam = tam;
        this.arregloUsuarios = new ListaUsuario[tam];
        for(int i=0;i<tam;i++){
            arregloUsuarios[i] = new ListaUsuario();
        }
    }

    public int hash(String nombreUsuario){
        int valor = 0;
        for(int i=0;i<nombreUsuario.length();i++){
            char c = nombreUsuario.charAt(i);
            int ascii = (int) c;
            valor = valor + ascii;
        }

        return valor%this.tam;
    }

    public boolean ingresar(Usuario usuario){
        int index = hash(usuario.getNombreUsuario());
        this.arregloUsuarios[index].ingresarUsuario(usuario);
        return true;
    }

    public Usuario buscar(String nombreUsuario){
        int index = hash(nombreUsuario);
        Usuario u = arregloUsuarios[index].buscarUsuario(nombreUsuario);
        if(u == null) return null;
        else return u;
    }

    public boolean eliminar(String nombreUsuario){
        int index = hash(nombreUsuario);
        if(this.arregloUsuarios[index].getPrimero() == null){
            return false;
        }else{
            if(this.arregloUsuarios[index].eliminar(nombreUsuario)) return true;
            else return false;
        }
    }
}
