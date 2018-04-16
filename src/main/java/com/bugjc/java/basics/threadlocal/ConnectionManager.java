package com.bugjc.java.basics.threadlocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionManager {

    private static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<Connection>();

    public static Connection getConnection() {
        if(connThreadLocal.get() != null)
            return connThreadLocal.get();

        Connection conn = getConnection();
        connThreadLocal.set(conn);
        return conn;
    }

}

