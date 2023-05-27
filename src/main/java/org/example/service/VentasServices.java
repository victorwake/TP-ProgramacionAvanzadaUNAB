package org.example.service;

import org.example.connectionDb.ConectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import static org.example.Main.clearScreen;

public class VentasServices {
    Scanner scanner = new Scanner(System.in);
    ProductoServices productoServices = new ProductoServices();
    ClienteServices clienteService = new ClienteServices();
    TicketServices ticketServices = new TicketServices();

    public void menuVenta() throws SQLException {
        boolean exit = false;

        while (!exit) {
            System.out.println("===== MENÚ =====");
            System.out.println("1. crear venta");
            System.out.println("2. Buscar producto");
            System.out.println("3. Crear cliente");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    clearScreen();
                    System.out.println("Ingrese el codigo de producto:");
                    String codProducto = scanner.nextLine();
                    int idProducto = obtenerIdProductoPorCodigo(codProducto);
                    System.out.println("Ingrese la cantidad:");
                    int cantidad = scanner.nextInt();

                    sumarProduct(codProducto, cantidad);

                    System.out.println("Ingrese el codigo del cliente:");
                    int codCliente = scanner.nextInt();
                    System.out.println("Ingrese el codigo del vendedor:");
                    int codVendedor = scanner.nextInt();

                    int nuevoIdVenta = guardarVenta(codVendedor, idProducto, codCliente);
                    double precioTotal = sumarProduct(codProducto, cantidad);

                    actualizarTotalVendido(nuevoIdVenta, precioTotal);

                    productoServices.actualizarStockProducto(codProducto, cantidad);

                    ticketServices.mostrarVentaPorId(nuevoIdVenta, cantidad);


                    break;
                case 2:
                    System.out.println("Ingrese el nombre del producto:");
                    String nombreProducto = scanner.nextLine();
                    productoServices.buscarPorNombre(nombreProducto);
                    break;
                case 3:
                    clearScreen();
                    clienteService.crearCliente();
                    break;
                case 6:
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
    public double sumarProduct(String codProducto, int cantidad) {
        double precioTotal = 0;
        try {
            Connection conn = ConectionSQL.getConnection();

            // Preparar la consulta para obtener el producto por el código
            String sql = "SELECT nombreProducto, precioProducto FROM producto WHERE codigoProducto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, codProducto);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombreProducto = rs.getString("nombreProducto");
                double precioProducto = rs.getDouble("precioProducto");

                precioTotal = precioProducto * cantidad;

//                System.out.println("Producto: " + nombreProducto);
//                System.out.println("Precio unitario: " + precioProducto);
//                System.out.println("Precio total por cantidad " + cantidad + ": " + precioTotal);
            } else {
                System.out.println("No se encontró el producto con el código " + codProducto);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener el producto: " + e.getMessage());
        }
        return precioTotal;
    }

//-------------------------------------------------------------------------------------------------------------//
public int guardarVenta(int vendedorId, int productoId, int clienteId) {
    int nuevoId = 0;
    try {
        Connection conn = ConectionSQL.getConnection();

        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Obtener el último ID de venta
        int ultimoId = obtenerUltimoIdVenta(conn);

        // Generar el nuevo ID sumando 1 al último ID
        nuevoId = ultimoId + 1;

        // Preparar la consulta de inserción
        String sql = "INSERT INTO ventas (ventaId, fecha, vendedorId, productoId, clienteId) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, nuevoId);
        stmt.setDate(2, new java.sql.Date(fechaActual.getTime()));
        stmt.setInt(3, vendedorId);
        stmt.setInt(4, productoId);
        stmt.setInt(5, clienteId);

        stmt.executeUpdate();

        stmt.close();
        conn.close();

//        System.out.println("Venta guardada correctamente. ID de venta: " + nuevoId);
    } catch (SQLException e) {
        System.out.println("Error al guardar la venta: " + e.getMessage());
    }
    return nuevoId;
}

//-------------------------------------------------------------------------------------------------------------//
    private int obtenerUltimoIdVenta(Connection conn) throws SQLException {
        int ultimoId = 0;

        // Preparar la consulta para obtener el último ID de venta
        String sql = "SELECT MAX(ventaId) AS ultimoId FROM ventas";
        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            ultimoId = rs.getInt("ultimoId");
        }

        rs.close();
        stmt.close();

        return ultimoId;
    }

//-------------------------------------------------------------------------------------------------------------//
    public int obtenerIdProductoPorCodigo(String codProducto) {
        int idProducto = 0;
        try {
            Connection conn = ConectionSQL.getConnection();

            // Preparar la consulta para obtener el ID del producto por el código
            String sql = "SELECT productoId FROM producto WHERE codigoProducto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, codProducto);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idProducto = rs.getInt("productoId");
            } else {
                System.out.println("No se encontró el producto con el código " + codProducto);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del producto: " + e.getMessage());
        }
        return idProducto;
    }

//-------------------------------------------------------------------------------------------------//

    public void actualizarTotalVendido(int ventaId, double totalVendido) {
        try {
            Connection conn = ConectionSQL.getConnection();

            // Preparar la consulta de actualización
            String sql = "UPDATE ventas SET totalVendido = ? WHERE ventaId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, totalVendido);
            stmt.setInt(2, ventaId);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

//            System.out.println("Total vendido actualizado correctamente para la venta con ID: " + ventaId);
        } catch (SQLException e) {
            System.out.println("Error al actualizar el total vendido: " + e.getMessage());
        }
    }
}
