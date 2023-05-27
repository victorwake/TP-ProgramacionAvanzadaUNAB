package org.example.service;

import org.example.connectionDb.ConectionSQL;
import java.sql.*;
import java.util.Scanner;
import static org.example.Main.clearScreen;

public class ProductoServices {
    Scanner scanner = new Scanner(System.in);

    //--Menu de gestion de productos--//
    public  void menuProductos() throws Exception {
        boolean exit = false;

        while (!exit) {

            System.out.println("===== MENÚ =====");
            System.out.println("1. Ingresar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Actualizar stock");
            System.out.println("4. Actualizar precio");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    clearScreen();
                    insertar();
                    break;
                case 2:
                    clearScreen();
                    mostrarProductos();
                    System.out.println(" ");
                    break;
                case 3:
                    clearScreen();
                    System.out.println("Ingrese el código del producto:");
                    scanner.nextLine();
                    String  codProducto = scanner.nextLine();
                    System.out.println("Ingrese la cantidad:");
                    int cantidad = scanner.nextInt();
                    agregarProductoStock(codProducto, cantidad);
                    System.out.println(" ");
                    break;
                case 4:
                    clearScreen();
                    System.out.println("Ingrese el código del producto:");
                    scanner.nextLine();
                    String  codProducto1 = scanner.nextLine();
                    System.out.println("Ingrese el nuevo precio:");
                    double precio = scanner.nextDouble();
                    actualizarPrecioProducto(codProducto1, precio);
                    System.out.println(" ");
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

    public void mostrarProductos() {
        try {
            Connection conn = ConectionSQL.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT ProductoId, CodigoProducto, NombreProducto, PrecioProducto, StockProducto FROM producto";
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                int productoId = resultSet.getInt("ProductoId");
                String codigoProducto = resultSet.getString("CodigoProducto");
                String nombreProducto = resultSet.getString("NombreProducto");
                double precioProducto = resultSet.getDouble("PrecioProducto");
                int stockProducto = resultSet.getInt("StockProducto");

                System.out.println("Producto ID: " + productoId);
                System.out.println("Código: " + codigoProducto);
                System.out.println("Nombre: " + nombreProducto);
                System.out.println("Precio: " + precioProducto);
                System.out.println("Stock: " + stockProducto);
                System.out.println("-----------------------");
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al mostrar los productos: " + e.getMessage());
        }
    }

//-------------------------------------------------------------------------------------------------------------//
    public void insertar() {
        try {
            Connection conn = ConectionSQL.getConnection();

            // Obtener el código del producto por consola
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingrese el código del producto: ");
            String codigoProducto = scanner.nextLine();

            // Verificar si el código de producto ya existe en la base de datos
            String consultaCodigo = "SELECT COUNT(*) FROM producto WHERE CodigoProducto = ?";
            PreparedStatement stmtConsultaCodigo = conn.prepareStatement(consultaCodigo);
            stmtConsultaCodigo.setString(1, codigoProducto);
            ResultSet resultSetCodigo = stmtConsultaCodigo.executeQuery();

            int count = 0;
            if (resultSetCodigo.next()) {
                count = resultSetCodigo.getInt(1);
            }

            if (count > 0) {
                System.out.println("El código de producto ya existe en la base de datos.");
                return; // Salir del método si el código de producto ya existe
            }

            // Consultar el último valor de ProductoId
            String consultaId = "SELECT MAX(ProductoId) FROM producto";
            Statement stmtConsultaId = conn.createStatement();
            ResultSet resultSetId = stmtConsultaId.executeQuery(consultaId);

            int ultimoId = 0;
            if (resultSetId.next()) {
                ultimoId = resultSetId.getInt(1);
            }

            // Generar el nuevo ProductoId sumándole 1
            int nuevoId = ultimoId + 1;

            System.out.print("Ingrese el nombre del producto: ");
            String nombreProducto = scanner.nextLine();

            System.out.print("Ingrese el precio del producto: ");
            double precioProducto = scanner.nextDouble();

            System.out.print("Ingrese el stock del producto: ");
            int stockProducto = scanner.nextInt();

            // Preparar la consulta de inserción
            String sql = "INSERT INTO producto (ProductoId, CodigoProducto, NombreProducto, PrecioProducto, StockProducto) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nuevoId);
            stmt.setString(2, codigoProducto);
            stmt.setString(3, nombreProducto);
            stmt.setDouble(4, precioProducto);
            stmt.setInt(5, stockProducto);

            stmt.executeUpdate();

            stmt.close();
            stmtConsultaId.close();
            stmtConsultaCodigo.close();
            conn.close();

            System.out.println("Se ha insertado el producto correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto: " + e.getMessage());
        }
    }
//-------------------------------------------------------------------------------------------------------------//
    public void buscarPorNombre(String nombre) {
        try {
            Connection conn = ConectionSQL.getConnection();
            String sql = "SELECT NombreProducto, StockProducto, CodigoProducto FROM producto WHERE LOWER(NombreProducto) LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nombre.toLowerCase() + "%");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String nombreProducto = resultSet.getString("NombreProducto");
                int stockProducto = resultSet.getInt("StockProducto");
                String codigoProducto = resultSet.getString("CodigoProducto");

                System.out.println("Nombre: " + nombreProducto);
                System.out.println("Stock: " + stockProducto);
                System.out.println("Código: " + codigoProducto);
                System.out.println("-----------------------");
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar productos por nombre: " + e.getMessage());
        }
    }

//-------------------------------------------------------------------------------------------------------------//
    public void actualizarStockProducto(String codigoProducto, int cantidad) throws SQLException {
        Connection conn = ConectionSQL.getConnection();
        if (conn == null) {
            System.out.println("Error de conexión a la base de datos.");
            return;
        }

        // Consultar el stock actual del producto
        String consultaStock = "SELECT stockProducto FROM Producto WHERE codigoProducto = ?";
        int stockActual = 0;

        try (PreparedStatement stmt = conn.prepareStatement(consultaStock)) {
            stmt.setString(1, codigoProducto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                stockActual = rs.getInt("stockProducto");
            } else {
                System.out.println("El producto con el código " + codigoProducto + " no existe.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Verificar si hay suficiente stock para realizar el descuento
        if (stockActual < cantidad) {
            System.out.println("No hay suficiente stock del producto.");
            return;
        }

        // Actualizar el stock del producto
        String actualizarStock = "UPDATE Producto SET stockProducto = ? WHERE codigoProducto = ?";

        try (PreparedStatement stmt = conn.prepareStatement(actualizarStock)) {
            int nuevoStock = stockActual - cantidad;
            stmt.setInt(1, nuevoStock);
            stmt.setString(2, codigoProducto);
            stmt.executeUpdate();

            System.out.println("Se actualizó el stock del producto con el código " + codigoProducto + ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//-------------------------------------------------------------------------------------------------------------//
    public void agregarProductoStock(String codigoProducto, int cantidad) throws SQLException {
        // Establecer la conexión a la base de datos
        Connection conn = ConectionSQL.getConnection();
        if (conn == null) {
            System.out.println("Error de conexión a la base de datos.");
            return;
        }

        // Consultar el stock actual del producto
        String consultaStock = "SELECT stockProducto FROM Producto WHERE codigoProducto = ?";
        int stockActual = 0;

        try (PreparedStatement stmt = conn.prepareStatement(consultaStock)) {
            stmt.setString(1, codigoProducto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                stockActual = rs.getInt("stockProducto");
            } else {
                System.out.println("El producto con el código " + codigoProducto + " no existe.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Verificar si hay suficiente stock para realizar el descuento
        if (stockActual < cantidad) {
            System.out.println("No hay suficiente stock del producto.");
            return;
        }

        // Actualizar el stock del producto
        String actualizarStock = "UPDATE Producto SET stockProducto = ? WHERE codigoProducto = ?";

        try (PreparedStatement stmt = conn.prepareStatement(actualizarStock)) {
            int nuevoStock = stockActual + cantidad;
            stmt.setInt(1, nuevoStock);
            stmt.setString(2, codigoProducto);
            stmt.executeUpdate();

            System.out.println("Se actualizó el stock del producto con el código " + codigoProducto + ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Cerrar la conexión a la base de datos
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//--------------------------------------------------------------------------------------------------------------//

    public void actualizarPrecioProducto(String codigoProducto, double nuevoPrecio) throws SQLException {

        Connection conn = ConectionSQL.getConnection();
        if (conn == null) {
            System.out.println("Error de conexión a la base de datos.");
            return;
        }

        // Actualizar el precio del producto
        String actualizarPrecio = "UPDATE Producto SET precioProducto = ? WHERE codigoProducto = ?";

        try (PreparedStatement stmt = conn.prepareStatement(actualizarPrecio)) {
            stmt.setDouble(1, nuevoPrecio);
            stmt.setString(2, codigoProducto);
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Se actualizó el precio del producto con el código " + codigoProducto + ".");
            } else {
                System.out.println("El producto con el código " + codigoProducto + " no existe.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
