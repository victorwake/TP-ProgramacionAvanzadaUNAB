package org.example.service;

import org.example.connectionDb.ConectionSQL;


import java.sql.*;
import java.util.Scanner;

import static org.example.Main.clearScreen;

public class ClienteServices {
    Scanner scanner = new Scanner(System.in);

    //--Menu de gestion de Clientes--//
    public  void menuClientes() throws Exception {
        boolean exit = false;

        while (!exit) {

            System.out.println("===== MENÚ =====");
            System.out.println("1. Ingresar nuevo Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente");
            System.out.println("4. Actualizar Cliente");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    clearScreen();
                    crearCliente();
                    break;
                case 2:
                    clearScreen();
                    listarClientes();
                    System.out.println(" ");
                    break;
                case 3:
                    clearScreen();
                    System.out.println("Ingrese el apellido del cliente: ");
                    scanner.nextLine();
                    String  apellido = scanner.nextLine();
                    buscarPorApellido(apellido);
                    System.out.println(" ");
                    break;
                case 4:
                    clearScreen();
                    menuUpdate();
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

    public  void menuUpdate() throws Exception {
        boolean exit = false;

        while (!exit) {

            System.out.println("===== MENÚ =====");
            System.out.println("1. Ingresar nuevo email");
            System.out.println("2. Ingresar nuevo telefono");
            System.out.println("3. Ingresar nuevo celular");
            System.out.println("4. Ingresar nueva direccion");

            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    clearScreen();
                    System.out.println("Ingrese el Id del Cliente: ");
                    scanner.nextLine();
                    int clienteId = scanner.nextInt();
                    actualizarEmail(clienteId);
                    System.out.println(" ");
                    break;
                case 2:
                    clearScreen();
                    System.out.println("Ingrese el Id del Cliente: ");
                    scanner.nextLine();
                    clienteId = scanner.nextInt();
                    actualizarTelefono(clienteId);
                    System.out.println(" ");
                    break;
                case 3:
                    clearScreen();
                    System.out.println("Ingrese el Id del Cliente: ");
                    scanner.nextLine();
                    clienteId = scanner.nextInt();
                    actualizarCelular(clienteId);
                    System.out.println(" ");
                    break;
                case 4:
                    clearScreen();
                    System.out.println("Ingrese el Id del Cliente: ");
                    scanner.nextLine();
                    clienteId = scanner.nextInt();
                    actualizarDireccion(clienteId);
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


    public void crearCliente() {
        try {
            Connection conn = ConectionSQL.getConnection();

            // Consultar el último valor de clienteId
            String consultaId = "SELECT MAX(clienteId) FROM cliente";
            var stmtConsultaId = conn.createStatement();
            ResultSet resultSetId = stmtConsultaId.executeQuery(consultaId);

            int ultimoId = 0;
            if (resultSetId.next()) {
                ultimoId = resultSetId.getInt(1);
            }

            // Generar el nuevo clienteId sumándole 1
            int nuevoId = ultimoId + 1;

            // Obtener los datos por consola
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingrese el nombre del cliente: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese el apellido del cliente: ");
            String apellido = scanner.nextLine();

            System.out.print("Ingrese el teléfono del cliente: ");
            String telefono = scanner.nextLine();

            System.out.print("Ingrese el celular del cliente: ");
            String celular = scanner.nextLine();

            System.out.print("Ingrese la dirección del cliente: ");
            String direccion = scanner.nextLine();

            System.out.print("Ingrese el email del cliente: ");
            String email = scanner.nextLine();

            // Preparar la consulta de inserción
            String sql = "INSERT INTO cliente (clienteId, nombre, apellido, telefono, celular, direccion, email) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nuevoId);
            stmt.setString(2, nombre);
            stmt.setString(3, apellido);
            stmt.setString(4, telefono);
            stmt.setString(5, celular);
            stmt.setString(6, direccion);
            stmt.setString(7, email);

            stmt.executeUpdate();// Ejecutar la consulta

            stmt.close();
            stmtConsultaId.close();
            conn.close();

            System.out.println("Se ha insertado el cliente correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el cliente: " + e.getMessage());
        }
    }

//-------------------------------------------------------------------------------------------------------------//
    public void actualizarTelefono(int clienteId) {
        try {
            Connection conn = ConectionSQL.getConnection();
            Scanner scanner = new Scanner(System.in);

            // Obtener los nuevos datos del cliente por consola
            System.out.print("Ingrese el nuevo teléfono del cliente: ");
            String telefono = scanner.nextLine();

            // Preparar la consulta de actualización
            String sql = "UPDATE cliente SET telefono = ? " +
                    "WHERE clienteId = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, telefono);
            stmt.setInt(2, clienteId);

            stmt.executeUpdate(); // Ejecutar la consulta de actualización

            stmt.close();
            conn.close();

            System.out.println("Se ha actualizado el telefono correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

//-------------------------------------------------------------------------------------------------------------//

    public void actualizarEmail(int clienteId) {
        try {
            Connection conn = ConectionSQL.getConnection();
            Scanner scanner = new Scanner(System.in);

            // Obtener los nuevos datos del cliente por consola
            System.out.print("Ingrese el nuevo email del cliente: ");
            String email = scanner.nextLine();

            // Preparar la consulta de actualización
            String sql = "UPDATE cliente SET email = ? " +
                    "WHERE clienteId = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setInt(2, clienteId);

            stmt.executeUpdate(); // Ejecutar la consulta de actualización

            stmt.close();
            conn.close();

            System.out.println("Se ha actualizado el cliente correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    //-------------------------------------------------------------------------------------------------------------//

    public void actualizarDireccion(int clienteId) {
        try {
            Connection conn = ConectionSQL.getConnection();
            Scanner scanner = new Scanner(System.in);

            // Obtener los nuevos datos del cliente por consola
            System.out.print("Ingrese la nueva dirección del cliente: ");
            String direccion = scanner.nextLine();


            // Preparar la consulta de actualización
            String sql = "UPDATE cliente SET direccion = ?" +
                    "WHERE clienteId = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, direccion);
            stmt.setInt(2, clienteId);

            stmt.executeUpdate(); // Ejecutar la consulta de actualización

            stmt.close();
            conn.close();

            System.out.println("Se ha actualizado la direccion correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    //-------------------------------------------------------------------------------------------------------------//

    public void actualizarCelular(int clienteId) {
        try {
            Connection conn = ConectionSQL.getConnection();
            Scanner scanner = new Scanner(System.in);

            // Obtener los nuevos datos del cliente por consola
            System.out.print("Ingrese el nuevo celular del cliente: ");
            String celular = scanner.nextLine();

            // Preparar la consulta de actualización
            String sql = "UPDATE cliente SET celular = ? " +
                    "WHERE clienteId = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, celular);
            stmt.setInt(2, clienteId);

            stmt.executeUpdate(); // Ejecutar la consulta de actualización

            stmt.close();
            conn.close();

            System.out.println("Se ha actualizado el celular correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    //-------------------------------------------------------------------------------------------------------------//
    public void listarClientes() {
        try {
            Connection conn = ConectionSQL.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM cliente";

            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int clienteId = resultSet.getInt("clienteId");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String telefono = resultSet.getString("telefono");
                String celular = resultSet.getString("celular");
                String direccion = resultSet.getString("direccion");
                String email = resultSet.getString("email");

                System.out.println("clienteId: " + clienteId);
                System.out.println("nombre: " + nombre);
                System.out.println("apellido: " + apellido);
                System.out.println("telefono: " + telefono);
                System.out.println("celular: " + celular);
                System.out.println("direccion: " + direccion);
                System.out.println("email: " + email);
                System.out.println();
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al mostrar la lista de clientes: " + e.getMessage());
        }
    }
//-------------------------------------------------------------------------------------------------------------//

    public void buscarPorApellido(String apellido) {
        try {
            Connection conn = ConectionSQL.getConnection();
            String sql = "SELECT * FROM cliente WHERE LOWER(apellido) LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + apellido.toLowerCase() + "%");
            ResultSet resultSet = stmt.executeQuery();

            boolean resultadosEncontrados = false; // Bandera para controlar si se encontraron resultados

            while (resultSet.next()) {
                int clienteIdResult = resultSet.getInt("clienteId");
                String apellidoResult = resultSet.getString("apellido");
                String nombreResult = resultSet.getString("nombre");
                String emailResult = resultSet.getString("email");
                String direccionResult = resultSet.getString("direccion");
                String celularResult = resultSet.getString("celular");

                System.out.println("ClienteId: " + clienteIdResult);
                System.out.println("Apellido: " + apellidoResult);
                System.out.println("Nombre: " + nombreResult);
                System.out.println("Email: " + emailResult);
                System.out.println("Dirección: " + direccionResult);
                System.out.println("Celular: " + celularResult);
                System.out.println("-----------------------");

                resultadosEncontrados = true; // Se encontraron resultados
            }

            if (!resultadosEncontrados) {
                System.out.println("No se encontraron resultados para la búsqueda del apellido: " + apellido);
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente por apellido: " + e.getMessage());
        }
    }

}






