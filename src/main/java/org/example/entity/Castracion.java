package org.example.entity;

import java.util.Date;

public class Castracion extends Servicio {
    private String tipoAnimal;
    private String observaciones;


    public Castracion() {
    }

    public Castracion(int servicioId, String tipoDeServicio, double precio, Date fecha, String tipoAnimal, String observaciones) {
        super(servicioId, tipoDeServicio, precio, fecha);
        this.tipoAnimal = tipoAnimal;
        this.observaciones = observaciones;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
