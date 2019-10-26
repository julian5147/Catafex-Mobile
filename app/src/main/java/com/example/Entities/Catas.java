package com.example.Entities;

import java.io.Serializable;
import java.sql.Time;


public class Catas implements Serializable {

    String codCatacion;

    String tipoCafe;

    Time hora;

    int veces;

    public String getTipoCafe() {
        return tipoCafe;
    }

    public void setTipoCafe(String tipoCafe) {
        this.tipoCafe = tipoCafe;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getVeces() {
        return veces;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }

    public String getCodCatacion() {
        return codCatacion;
    }

    public void setCodCatacion(String codCatacion) {
        this.codCatacion = codCatacion;
    }
}
