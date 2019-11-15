package com.example.Entities;

import java.io.Serializable;
import java.sql.Time;


public class Catas implements Serializable {

    String codCafe;

    int vezCatada;

    String hora;

    String fecha;

    String tipoCafe;

    public String getTipoCafe() {
        return tipoCafe;
    }

    public void setTipoCafe(String tipoCafe) {
        this.tipoCafe = tipoCafe;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getVezCatada() {
        return vezCatada;
    }

    public void setVezCatada(int vezCatada) {
        this.vezCatada = vezCatada;
    }

    public String getCodCafe() {
        return codCafe;
    }

    public void setCodCafe(String codCafe) {
        this.codCafe = codCafe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
