package org.example.service;

import org.example.connectionDb.ConectionSQL;
import org.example.enums.Vendedores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.example.ui.Menu.clearScreen;

public class TicketServices {

    public void generarTicket(int nuevoIdVenta) {
        System.out.println("Generando ticket...");



    }

//-------------------------------------------------------------------------------------------------------------//

public void mostrarTicket(int ventaId) {
        System.out.println("Mostrando ticket...");

    }


//-------------------------------------------------------------------------------------------------------------//

    public void mostrarVentaPorId(int ventaId, int cantidad) {
        try {
            Connection conn = ConectionSQL.getConnection();

            // Preparar la consulta para obtener la venta por ID
            String sql = "SELECT v.VentaId, v.Fecha, v.TotalVendido, p.NombreProducto, p.PrecioProducto, c.Nombre, c.Apellido, v.VendedorId " +
                    "FROM ventas v " +
                    "JOIN producto p ON v.ProductoId = p.ProductoId " +
                    "JOIN cliente c ON v.ClienteId = c.ClienteId " +
                    "WHERE v.VentaId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ventaId);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Mostrar el resultado
            if (rs.next()) {
                Date fecha = rs.getDate("Fecha");
                double totalVendido = rs.getDouble("TotalVendido");
                String nombreProducto = rs.getString("NombreProducto");
                double precioProducto = rs.getDouble("PrecioProducto");
                String nombreCliente = rs.getString("Nombre");
                String apellidoCliente = rs.getString("Apellido");
                int vendedorId = rs.getInt("VendedorId");

                // Obtener el nombre del vendedor a partir del enum Vendedores
                String nombreVendedor = obtenerNombreVendedor(vendedorId);

                clearScreen();
                System.out.println("-----Ticket de Venta-----");
                System.out.printf("Venta ID: %d%n", ventaId);
                System.out.printf("Cliente: %s %s%n", nombreCliente, apellidoCliente);
                System.out.printf("Vendedor: %s%n", nombreVendedor);
                System.out.printf("Fecha: %s%n", fecha);
                System.out.println(" ------------------------ ");
                System.out.println("Producto: " + nombreProducto);
                System.out.printf("cantidad: %d%nValor Unitario: %.2f%n", cantidad, precioProducto);
                System.out.println();
                System.out.printf("Precio final: %.2f%n", totalVendido);
                System.out.println(" ------------------------ ");
                System.out.println("Gracias por su compra!");
                System.out.println(" ");
            } else {
                System.out.println("Venta no encontrada");
            }

            // Cerrar recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener la venta: " + e.getMessage());
        }
    }

    private String obtenerNombreVendedor(int vendedorId) {
        Vendedores[] vendedores = Vendedores.values();

        if (vendedorId >= 1 && vendedorId <= vendedores.length) {
            return vendedores[vendedorId - 1].getNombre();
        } else {
            return "Vendedor no encontrado";
        }
    }


//-------------------------------------------------------------------------------------------------------------//
}
