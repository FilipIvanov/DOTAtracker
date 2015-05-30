/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author WakingBliss
 */
public class Login {
    
    private static Connection connection;

// Pristupa drajveru u JAR fajlu
private static Connection createConnection() {
    try {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://sql5.freesqldatabase.com:3306/sql578801", "sql578801", "qC5!tL7%");
    } catch (Exception e) {
        return null;
    }
}

public static Connection get() {
    if (connection == null) {
        connection = createConnection();
    }
    return connection;
}
    
}
