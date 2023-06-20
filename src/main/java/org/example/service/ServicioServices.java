package org.example.service;

import java.util.Scanner;

import static org.example.ui.Menu.clearScreen;

public class ServicioServices {
    Scanner scanner = new Scanner(System.in);

    public  void menuServicio() throws Exception {
        boolean exit = false;

        while (!exit) {

            System.out.println("===== MENÚ =====");
            System.out.println("=== SERVICIO ===");
            System.out.println("1. seleccionar Servicio");
            System.out.println("2. Buscar Servicio por cliente");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    clearScreen();
                    servicio();
                    break;
                case 2:
                    clearScreen();

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
public  void servicio() throws Exception {
    boolean exit = false;

    while (!exit) {

        System.out.println("===== MENÚ =====");
        System.out.println("=== SERVICIO ===");
        System.out.println("1. Consulta");
        System.out.println("2. Castracion");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                clearScreen();
                consulta();
                break;
            case 2:
                clearScreen();
                castracion();
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
public  void consulta() throws Exception {
    boolean exit = false;

    while (!exit) {

        System.out.println("===== MENÚ =====");
        System.out.println("=== CONSULTA ===");
        System.out.println("1. Ingresar datos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                clearScreen();

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
public  void castracion() throws Exception {
    boolean exit = false;

    while (!exit) {

        System.out.println("=====  MENÚ  =====");
        System.out.println("=== CASTRACION ===");
        System.out.println("1. Ingresar datos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                clearScreen();

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
//-------------------------------------------------------------------------------------------------------------//

}
