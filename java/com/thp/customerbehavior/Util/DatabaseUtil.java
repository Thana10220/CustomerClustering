package com.thp.customerbehavior.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil extends ParameterUtil{

    SecurityUtil SECURITY = new SecurityUtil();

    public Connection connectSQLServer() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //DriverManager.setLoginTimeout(5);
        String dbURL = "jdbc:sqlserver://"+SECURITY.Decode(DB_COMPONENT_HOST)+":"+SECURITY.Decode(DB_COMPONENT_PORT)+";";
        Connection connection;
        connection = DriverManager.getConnection(dbURL, SECURITY.Decode(DB_COMPONENT_AUTH_U), SECURITY.Decode(DB_COMPONENT_AUTH_P));
        return connection;
    }



}
