package com.example.manualnetorange;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class PostgreSQLConnection {

    // Parámetros de conexión
    private static final String URL = "jdbc:postgresql://ep-solitary-hall-a2pwd4fg.eu-central-1.aws.neon.tech:5432/ProyectoMulti";
    private static final String USER = "ProyectoMulti_owner";
    private static final String PASSWORD = "Xmn5zOt1AZES";
    private Connection connection;

    // Método para establecer la conexión
    public void connect() {
        try {
            // Cargar el driver
            Class.forName("org.postgresql.Driver");
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Método para realizar una consulta
    public ResultSet queryDatabase(String query) {
        Statement stmt = null;
        ResultSet rs= null;
        try {
             stmt = connection.createStatement();
             rs = stmt.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    // Método para cerrar la conexión
    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return  connection;
    }
}
