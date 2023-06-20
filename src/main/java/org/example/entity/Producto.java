package org.example.entity;

public class Producto {

    private int productoId;
    private String codigoProducto;
    private String nombreProducto;
    private double precioProducto;
    private int stockProducto;

    public Producto() {
    }

    public Producto(int productoId, String codigoProducto, String nombreProducto, double precioProducto, int stockProducto) {
        this.productoId = productoId;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public void limpiarValores() {
        this.productoId = 0;
        this.codigoProducto = "";
        this.nombreProducto = "";
        this.precioProducto = 0.0;
        this.stockProducto = 0;
    }

}


