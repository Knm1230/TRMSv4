package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection
{
    private static Connection conn = null;
    public static Connection getConn()
    {
        if(conn == null)
        {
            //URL/Endpoint
            String endpoint = "database-1.c2yexpatug2t.us-east-1.rds.amazonaws.com";
            //URL
            String url = "jdbc:postgresql://" + endpoint + "/postgres";
            //Username
            String user = System.getenv("AWS_DB_UNAME");
            //Password
            String pass = System.getenv("AWS_DB_PWORD");

            try
            {
                conn = DriverManager.getConnection(url, user, pass);
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
