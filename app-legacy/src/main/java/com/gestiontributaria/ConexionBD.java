package com.gestiontributaria;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Configuración para tu Docker Oracle XE
    private static final String URL = "jdbc:oracle:thin:@localhost:51521/XEPDB1";
    private static final String USER = "GESTION_TRIBUTARIA";
    private static final String PASS = "admin123";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
