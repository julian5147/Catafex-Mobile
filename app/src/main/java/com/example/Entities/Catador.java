package com.example.Entities;

import java.io.Serializable;

public class Catador implements Serializable {

    private String nombre;

    private String cedula;

    private String correo;

    private String contrasena;

    private  String nivelExp;

    private  String codigo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNivelExp() {
        return nivelExp;
    }

    public void setNivelExp(String nivelExp) {
        this.nivelExp = nivelExp;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
