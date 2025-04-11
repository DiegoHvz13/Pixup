package org.DiegoHVZ.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexion<T> {
    public static String user = "root";
    public static String password = "n0m3l0";
    public static String db = "pixup";
    public static String server = "127.0.0.1";
    protected Connection connection;

    public Conexion() {
    }

    public Connection getConnection() {
        return this.connection;
    }

    public boolean testDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean loadConnection(String user, String password, String db, String server) {
        String url = null;
        if (user == null || password == null || db == null || server == null) {
            System.out.println("Los parámetros de conexión no pueden ser nulos.");
            return false;
        }
        if ("".equals(user) || "".equals(password) || "".equals(db) || "".equals(server)) {
            System.out.println("Los parámetros de conexión no pueden estar vacíos.");
            return false;
        }
        url = String.format("jdbc:mysql://%s/%s?user=%s&password=%s", server, db, user, password);
        try {
            if (!testDriver()) {
                System.out.println("El driver de MySQL no se pudo cargar.");
                return false;
            }
            connection = DriverManager.getConnection(url);
            return connection != null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al establecer la conexión: " + ex.getMessage());
        }
        return false;
    }


    public boolean openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                if (!loadConnection(user, password, db, server)) {
                    System.out.println("Error al cargar la conexión.");
                    return false;
                }
            }
            System.out.println("Conexión abierta: " + (connection != null && !connection.isClosed()));
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.out.println("Error al abrir la conexión: " + e.getMessage());
            return false;
        }
    }



    public void closeConnection() {
        try {
            if (connection == null) {
                return;
            }
            if (connection.isClosed()) {
                return;
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
