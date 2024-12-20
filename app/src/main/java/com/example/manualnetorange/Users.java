package com.example.manualnetorange;

import android.widget.Toast;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
    private int id;
    private String username;
    private String email;
    private String password;
    private int messages_left;
    private int group;
    private int type;

    public Users(int id, String username, String email, String password, int messages_left, int group, int type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.messages_left = messages_left;
        this.group = group;
        this.type = type;
    }

    public Users() {

    }

    public static boolean ValidateCredentials(String username, String password) throws SQLException {
        boolean isValid = false;
        String query = "SELECT username FROM \"Users\" WHERE username = ? AND password = ?";

        PostgreSQLConnection dbConnection = new PostgreSQLConnection();
        PreparedStatement stmt;
        try (Connection connection = dbConnection.connect()) {
            stmt = connection.prepareStatement(query);
        }
        stmt.setString(1, username.trim());
        stmt.setString(2, password.trim());

        try (ResultSet rs = stmt.executeQuery()) {
            isValid = rs.next();
        } catch (SQLException e) {
            System.err.println("Error validando las credenciales: " + e.getMessage());

        }
        return isValid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMessages_left() {
        return messages_left;
    }

    public void setMessages_left(int messages_left) {
        this.messages_left = messages_left;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
