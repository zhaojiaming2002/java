package net.microsoft.java.web.foundational.util;

import java.io.InputStreamReader;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description:JDBC工具类
 * @Date on 2022/4/12
 * @author:Suche
 **/

public final class JDBCUtil {

    static String url = null;
    static String user = null;
    static String password = null;
    static String driver = null;


    static {
        Properties properties = new Properties();
        try {
            properties.load(JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            user = (String) properties.get("jdbc.user");
            url = (String) properties.get("jdbc.url");
            password = (String) properties.get("jdbc.password");
            driver = (String) properties.get("jdbc.driver");
            System.out.println(url);
            System.out.println(user);
            System.out.println(password);
            System.out.println(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
