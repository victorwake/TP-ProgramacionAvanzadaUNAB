package org.example.entity;

import java.util.Date;

public class Consulta extends Servicio {
    private String motivoConsulta;
    private String diagnostico;


    public Consulta() {
    }

    public Consulta(int servicioId, String tipoDeServicio, double precio, Date fecha, String motivoConsulta, String diagnostico) {
        super(servicioId, tipoDeServicio, precio, fecha);
        this.motivoConsulta = motivoConsulta;
        this.diagnostico = diagnostico;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}
