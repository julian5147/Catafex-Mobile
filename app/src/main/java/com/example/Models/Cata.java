package com.example.Models;

import java.io.Serializable;

public class Cata implements Serializable {

    private String codCatacion;

    private int fragancia;

    private int aroma;

    private int acidez;

    private  int dulce;

    private int amargo;

    private int cuerpo;

    private int rancidez;

    private int saborResidual;

    private int impresionGlobal;
    private  String observaciones;


    public int getFragancia() {
        return fragancia;
    }

    public void setFragancia(int fragancia) {
        this.fragancia = fragancia;
    }

    public int getAroma() {
        return aroma;
    }

    public void setAroma(int aroma) {
        this.aroma = aroma;
    }

    public int getAcidez() {
        return acidez;
    }

    public void setAcidez(int acidez) {
        this.acidez = acidez;
    }

    public int getAmargo() {
        return amargo;
    }

    public void setAmargo(int amargo) {
        this.amargo = amargo;
    }

    public int getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(int cuerpo) {
        this.cuerpo = cuerpo;
    }

    public int getRancidez() {
        return rancidez;
    }

    public void setRancidez(int rancidez) {
        this.rancidez = rancidez;
    }

    public int getDulce() {
        return dulce;
    }

    public void setDulce(int dulce) {
        this.dulce = dulce;
    }

    public int getSaborResidual() {
        return saborResidual;
    }

    public void setSaborResidual(int saborResidual) {
        this.saborResidual = saborResidual;
    }

    public int getImpresionGlobal() {
        return impresionGlobal;
    }

    public void setImpresionGlobal(int impresionGlobal) {
        this.impresionGlobal = impresionGlobal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCodCatacion() {
        return codCatacion;
    }

    public void setCodCatacion(String codCatacion) {
        this.codCatacion = codCatacion;
    }


}
