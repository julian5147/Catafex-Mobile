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

    public String getCodPanel() {
        return codPanel;
    }

    public String getCodCatador() {
        return codCatador;
    }

    public String getCodCafe() {
        return codCafe;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
