package org.example.entity;

import java.util.Date;

public class Venta {
    private int ventaId;
    private Date fecha;

    private String[] productoArray;
    private double totalVendido;
    private Vendedor vendedor;
    private Producto producto;
    private Servicio servicio;
    private Cliente cliente;

    public Venta() {
    }

    public Venta(int ventaId, Date fecha, String[] productoArray, double totalVendido, Vendedor vendedor, Producto producto, Servicio servicio, Cliente cliente) {
        this.ventaId = ventaId;
        this.fecha = fecha;
        this.productoArray = productoArray;
        this.totalVendido = totalVendido;
        this.vendedor = vendedor;
        this.producto = producto;
        this.servicio = servicio;
        this.cliente = cliente;
    }

    public String[] getProductoArray() {
        return productoArray;
    }

    public void setProductoArray(String[] productoArray) {
        this.productoArray = productoArray;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(double totalVendido) {
        this.totalVendido = totalVendido;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}