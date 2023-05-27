package org.example.entity;

import org.example.enums.Vendedores;

public class Vendedor {
    private int vendedorId;
    private Vendedores nombre;
    private int ventaId;
    private Venta ventas;

    public Vendedor() {
    }

    public Vendedor(int vendedorId, Vendedores nombre, int ventaId, Venta ventas) {
        this.vendedorId = vendedorId;
        this.nombre = nombre;
        this.ventaId = ventaId;
        this.ventas = ventas;
    }

    public int getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    public Vendedores getNombre() {
        return nombre;
    }

    public void setNombre(Vendedores nombre) {
        this.nombre = nombre;
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