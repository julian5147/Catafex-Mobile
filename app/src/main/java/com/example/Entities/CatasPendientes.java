package com.example.Entities;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


public class CatasPendientes implements Serializable {

    private String codCafe;

    private int vezCatada;

    private String hora;

    private String fecha;

    private String tipoCafe;

    private String atributos;

    private String valoresDefecto;

    public String getTipoCafe() {
        return tipoCafe;
    }

    public String getHora() {
        return hora;
    }

    public int getVezCatada() {
        return vezCatada;
    }

    public String getCodCafe() {
        return codCafe;
    }

    public String getFecha() {
        return fecha;
    }

    public List<String> getAtributos() {
        return Arrays.asList(this.atributos.split(";"));
    }

    public List<String> getValoresDefecto(){
        return Arrays.asList((this.valoresDefecto.split(";")));
    }
}
