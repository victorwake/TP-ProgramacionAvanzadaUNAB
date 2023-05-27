package org.example.entity;

public class Ticket {
    private int ticketId;
    private int ventaId;
    private Venta ventas;

    public Ticket() {
    }

    public Ticket(int ticketId, int ventaId, Venta ventas) {
        this.ticketId = ticketId;
        this.ventaId = ventaId;
        this.ventas = ventas;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public Venta getVentas() {
        return ventas;
    }

    public void setVentas(Venta ventas) {
        this.ventas = ventas;
    }
}