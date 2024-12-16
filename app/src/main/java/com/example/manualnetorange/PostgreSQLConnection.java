package com.example.manualnetorange;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLConnection {

    // Parámetros de conexión
    private static final String URL = "jdbc:postgresql://ep-solitary-hall-a2pwd4fg.eu-central-1.aws.neon.tech:5432/ProyectoMulti";
    private static final String USER = "ProyectoMulti_owner";
    private static final String PASSWORD = "Xmn5zOt1AZES";

    /**
     * Método para establecer una conexión a la base de datos.
     *
     * @return Connection - la conexión establecida.
     */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Método para realizar una consulta a la base de datos.
     *
     * @param query La consulta SQL a ejecutar.
     * @return ResultSet con los resultados de la consulta.
     */
    public ResultSet queryDatabase(String query) {
        ResultSet resultSet = null;

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            // Ejecutar la consulta
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            System.err.println("Error ejecutando la consulta: " + e.getMessage());
            e.printStackTrace();
        }

        return resultSet;
    }

    /**
     * Método para cerrar la conexión y otros recursos.
     *
     * @param connection La conexión a cerrar.
     */
    public void close(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error cerrando la conexión: " + e.getMessage());
        }
    }


}
