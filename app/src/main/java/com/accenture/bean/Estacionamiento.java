package com.accenture.bean;

/**
 * Created by raulzamora on 06/11/17.
 */

public class Estacionamiento {
    String cajon;
    String dia;
    String tiempo;
    String status;

    @Override
    public String toString() {
        return   cajon +' '+ dia +' '+ tiempo +' '+ status;
    }

    public Estacionamiento(String cajon, String dia, String tiempo, String status) {
        this.cajon = cajon;
        this.dia = dia;
        this.tiempo = tiempo;
        this.status = status;
    }

    public String getCajon() {
        return cajon;
    }

    public void setCajon(String cajon) {
        this.cajon = cajon;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
