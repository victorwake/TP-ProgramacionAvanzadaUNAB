package org.example.connectionDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionSQL {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
//            System.out.println("Conectado a la base de datos");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/veterinaria?useTimezone=true&serverTimezone=UTC",
                    "root",
                    "root");
        }
        return connection;
    }

//    public static void closeConnection() throws SQLException {
//        if (connection != null && !connection.isClosed()) {
//            connection.close();
////            System.out.println("Desconectado de la base de datos");
//        }
//    }
}





