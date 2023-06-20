package org.example.service;

import org.example.connectionDb.ConectionSQL;
import org.example.entity.Vendedor;
import org.example.enums.Vendedores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import static org.example.ui.Menu.clearScreen;

public class VendedorServices {
    Scanner scanner = new Scanner(System.in);

    public  void menuVendedores() throws Exception {
        boolean exit = false;

        while (!exit) {

            System.out.println("===== MENÚ =====");
            System.out.println("1. Obtener nombre de vendedor");
            System.out.println("2. Listar ventas por vendedor");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    clearScreen();
                    System.out.println("Ingrese el Id del vendedor:");
                    int vendedorId = scanner.nextInt();
                    String nombreVendedor = obtenerNombreVendedor(vendedorId);
                    System.out.println("El nombre del vendedor es: " + nombreVendedor);
                    System.out.println(" ");
                    break;
                case 2:
                    clearScreen();
                    System.out.println("Ingrese el Id del vendedor:");
                    vendedorId = scanner.nextInt();
                    mostrarVentasPorVendedor(vendedorId);
                    System.out.println(" ");
                    break;
                case 0:
                    clearScreen();
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

//-------------------------------------------------------------------------------------------------------------//

    public String obtenerNombreVendedor(int vendedorId) {
        Vendedores[] vendedores = Vendedores.values();

        if (vendedorId >= 1 && vendedorId <= vendedores.length) {
            return vendedores[vendedorId - 1].getNombre();
        } else {
            return "Vendedor no encontrado";
        }
    }

//-------------------------------------------------------------------------------------------------------------//

    public void mostrarVentasPorVendedor(int vendedorId) {
        try {
            Connection conn = ConectionSQL.getConnection();

            // Preparar la consulta para obtener las ventas del vendedor
            String sql = "SELECT v.VentaId, v.Fecha, v.TotalVendido, p.NombreProducto, c.Nombre, c.Apellido " +
                    "FROM ventas v " +
                    "JOIN producto p ON v.ProductoId = p.ProductoId " +
                    "JOIN cliente c ON v.ClienteId = c.ClienteId " +
                    "WHERE v.VendedorId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vendedorId);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Mostrar los resultados
            while (rs.next()) {
                int ventaId = rs.getInt("VentaId");
                Date fecha = rs.getDate("Fecha");
                double totalVendido = rs.getDouble("TotalVendido");
                String nombreProducto = rs.getString("NombreProducto");
                String nombreCliente = rs.getString("Nombre");
                String apellidoCliente = rs.getString("Apellido");


                System.out.println("Venta ID: " + ventaId);
                System.out.println("Fecha: " + fecha);
                System.out.println("Total Vendido: " + totalVendido);
                System.out.println("Producto: " + nombreProducto);
                System.out.println("Cliente: " + nombreCliente + " " + apellidoCliente);
                System.out.println("------------------------");
            }

            // Cerrar recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener las ventas del vendedor: " + e.getMessage());
        }
    }

//-------------------------------------------------------------------------------------------------------------//


//-------------------------------------------------------------------------------------------------------------//


//-------------------------------------------------------------------------------------------------------------//
}
