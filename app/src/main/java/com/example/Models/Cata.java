package com.example.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cata {
    @SerializedName("codCatacion")
    @Expose
    private String codCatacion;
    @SerializedName("fragancia")
    @Expose
    private int fragancia;
    @SerializedName("aroma")
    @Expose
    private int aroma;
    @SerializedName("acidez")
    @Expose
    private int acidez;
    @SerializedName("dulce")
    @Expose
    private  int dulce;
    @SerializedName("amargo")
    @Expose
    private int amargo;
    @SerializedName("cuerpo")
    @Expose
    private int cuerpo;
    @SerializedName("rancidez")
    @Expose
    private int rancidez;
    @SerializedName("residual")
    @Expose
    private int saborResidual;
    @SerializedName("global")
    @Expose
    private int impresionGlobal;
    @SerializedName("observaciones")
    @Expose
    private  String observaciones;

    public Cata(String codCatacion, int fragancia, int aroma, int acidez, int dulce, int amargo, int cuerpo, int rancidez, int saborResidual, int impresionGlobal, String observaciones) {
        this.codCatacion = codCatacion;
        this.fragancia = fragancia;
        this.aroma = aroma;
        this.acidez = acidez;
        this.dulce = dulce;
        this.amargo = amargo;
        this.cuerpo = cuerpo;
        this.rancidez = rancidez;
        this.saborResidual = saborResidual;
        this.impresionGlobal = impresionGlobal;
        this.observaciones = observaciones;
    }

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
