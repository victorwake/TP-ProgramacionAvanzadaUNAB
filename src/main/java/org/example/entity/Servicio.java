package org.example.entity;
import java.util.Date;

public class Servicio {
    private int servicioId;
    private String tipoDeServicio;
    private double precio;
    private Date fecha;

    public Servicio() {
    }

    public Servicio(int servicioId, String tipoDeServicio, double precio, Date fecha) {
        this.servicioId = servicioId;
        this.tipoDeServicio = tipoDeServicio;
        this.precio = precio;
        this.fecha = fecha;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public String getTipoDeServicio() {
        return tipoDeServicio;
    }

    public void setTipoDeServicio(String tipoDeServicio) {
        this.tipoDeServicio = tipoDeServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}