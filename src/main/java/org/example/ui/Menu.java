package org.example.ui;

import org.example.connectionDb.ConectionSQL;
import org.example.service.*;

import java.util.Scanner;

public class Menu {

    public static void clearScreen() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public void mostrarMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        ConectionSQL conectionSQL = new ConectionSQL();
        conectionSQL.getConnection();

        ProductoServices productoServices = new ProductoServices();
        ClienteServices clienteService = new ClienteServices();
        VentasServices ventasServices = new VentasServices();
        VendedorServices vendedorServices = new VendedorServices();
        ServicioServices servicioServices = new ServicioServices();

        boolean exit = false;

        while (!exit) {
            System.out.println("===== MENÚ =====");
            System.out.println("1. Registrar venta");
            System.out.println("2. Consultar stock");
            System.out.println("==== GESTIÓN ====");
            System.out.println("3. Gestionar clientes");
            System.out.println("4. Gestionar Productos");
            System.out.println("5. Gestionar Vendedores");
            System.out.println("6. Gestionar Servicios");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    clearScreen();
                    ventasServices.menuVenta();
                    break;
                case 2:
                    clearScreen();
                    System.out.println("Ingrese el nombre del producto:");
                    scanner.nextLine();
                    String nombreProducto = scanner.nextLine();
                    productoServices.buscarPorNombre(nombreProducto);
                    System.out.println(" ");
                    break;
                case 3:
                    clearScreen();
                    clienteService.menuClientes();
                    System.out.println(" ");
                    break;
                case 4:
                    clearScreen();
                    productoServices.menuProductos();
                    break;
                case 5:
                    clearScreen();
                    vendedorServices.menuVendedores();
                    break;
                case 6:
                    clearScreen();
                    servicioServices.menuServicio();
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

        System.out.println("¡Hasta pronto!");
    }
}
