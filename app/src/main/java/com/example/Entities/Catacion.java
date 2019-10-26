package com.example.Entities;

import java.io.Serializable;

public class Catacion implements Serializable {
    String codCatacion;

    String codPanel;

    String codCatador;

    String codCafe;

    int cantidad;

    public String getCodCatacion() {
        return codCatacion;
    }

    public void setCodCatacion(String codCatacion) {
        this.codCatacion = codCatacion;
    }

    public String getCodPanel() {
        return codPanel;
    }

    public void setCodPanel(String codPanel) {
        this.codPanel = codPanel;
    }

    public String getCodCatador() {
        return codCatador;
    }

    public void setCodCatador(String codCatador) {
        this.codCatador = codCatador;
    }

    public String getCodCafe() {
        return codCafe;
    }

    public void setCodCafe(String codCafe) {
        this.codCafe = codCafe;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
