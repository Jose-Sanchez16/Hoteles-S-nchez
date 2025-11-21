package com.mycompany.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/hotelesSánchez";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection = null;

    // Constructor privado para evitar instanciación
    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión a la base de datos establecida.");
            } catch (ClassNotFoundException e) {
                System.err.println("Error: Driver JDBC no encontrado.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error al conectar con la base de datos:");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión:");
                e.printStackTrace();
            }
        }
    }
}
