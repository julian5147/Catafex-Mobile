package com.example.Entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


public class Catas implements Serializable {

    private String codCafe;

    private int vezCatada;

    private String hora;

    private String fecha;

    private String tipoCafe;

    private String atributos;

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

    public List<String> getAtributos() {
        return Arrays.asList(this.atributos.split(";"));
    }

    public void setAtributos(String atributos) {
        this.atributos = atributos;
    }
}
