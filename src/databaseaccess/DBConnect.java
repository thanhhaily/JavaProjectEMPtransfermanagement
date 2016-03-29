/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseaccess;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author EnablePassword
 */
public class DBConnect {
    public static String message;
    Connection conn;
    String url, serverName, databaseName, userName, password;

    public DBConnect() {
        url = "jdbc:sqlserver://";
        serverName = "localhost:1433";
        databaseName = "ETM";
        userName = "sa";
        password = "123456";
    }

    private String getConnectionUrl() {
        return url + serverName + ";DatabaseName =  " + databaseName;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(getConnectionUrl(), userName, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
