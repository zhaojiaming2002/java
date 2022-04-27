package net.microsoft.java.web.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @description:Druid连接池的使用
 * @Date on 2022/4/25
 * @author:suche
 **/

public class DruidDataSourceUtil {
    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        try {
            properties.load(DruidDataSourceUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        System.out.println(dataSource.getClass());
        return dataSource;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (null != resultSet) {
                resultSet.close();
            }
            if (null != statement) {
                statement.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
