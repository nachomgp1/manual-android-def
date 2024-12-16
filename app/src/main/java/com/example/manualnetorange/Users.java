package com.example.manualnetorange;

import org.postgresql.util.PSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        PostgreSQLConnection connection = new PostgreSQLConnection();
        connection.connect();
        String query = "SELECT 1 FROM \"Users\" WHERE username = ? AND password = ?";

        try (PreparedStatement stm = connection.getConnection().prepareStatement(query)) {
            stm.setString(1, username.trim());
            stm.setString(2, password.trim());
            try (ResultSet rs = stm.executeQuery()) {
                //If there are results credentials are true
                isValid = rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }

        }
        return  isValid;
    }

    public static List<String> ObtainUsername() throws SQLException {
        List<String> usernames = new ArrayList<>();
        PostgreSQLConnection connection = new PostgreSQLConnection();
        connection.connect();
        String query = "SELECT username FROM \"Users\" ";
        ResultSet rs = connection.queryDatabase(query);

        while (rs.next()) {
            usernames.add(rs.getString("username"));
        }
        rs.close();
        connection.close();

        return usernames;
    }

    public static List<String> ObtainPassword() throws SQLException {
        List<String> passwords = new ArrayList<>();
        PostgreSQLConnection connection = new PostgreSQLConnection();
        connection.connect();
        String query = "SELECT password FROM \"Users\" ";
        ResultSet rs = connection.queryDatabase(query);

        while (rs.next()) {
            passwords.add(rs.getString("password"));
        }
        rs.close();
        connection.close();

        return passwords;
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
