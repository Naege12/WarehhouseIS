package org.example.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static Connection ConnectDB() throws ClassNotFoundException, SQLException
    {
        String url = "jdbc:postgresql://localhost:5432/warehouse_db";
        String user = "postgres";
        String passvord = "190715";

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(url, user, passvord);
        return con;
    }

    public static boolean isConnect() throws ClassNotFoundException
    {
        try(Connection con = ConnectDB())
        {
            return true;
        }
        catch (SQLException  e)
        {
            System.out.println(e);
            return false;
        }

    }
}
