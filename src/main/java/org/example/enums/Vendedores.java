package org.example.enums;

public enum Vendedores {
    JAVIER("Javier Gonzales"),
    MARIELA("Mariela Perez"),
    LEONEL("Leonel Gomez"),
    LUCIANA("Luciana Rodriguez");

    private final String nombre;

    Vendedores(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
