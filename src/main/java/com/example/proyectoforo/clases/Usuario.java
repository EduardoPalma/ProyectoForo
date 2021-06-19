package com.example.proyectoforo.clases;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombreUsuario;
    private String pw;
    private String correo;

    public Usuario(String nombreUsuario,String pw,String correo){
        this.nombreUsuario = nombreUsuario;
        this.pw = pw;
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPw() {
        return pw;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
