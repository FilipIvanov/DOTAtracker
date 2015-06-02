/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author WakingBliss
 */
public class Login {
    
    private static Connection connection;

//pristup drajveru
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

public static ResultSet query(String sql) {
		try {
			Connection connection = get();
			Statement s = connection.createStatement();
			return s.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean exec(String sql, Object... values) {
		return exec(String.format(sql, values));
	}

	public static boolean exec(String sql) {
		try {
			Connection connection = get();
			Statement s = connection.createStatement();
			s.execute(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


    
}
