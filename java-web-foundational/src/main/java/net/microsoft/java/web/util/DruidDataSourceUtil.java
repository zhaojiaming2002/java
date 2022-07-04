package net.microsoft.java.web.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.cj.jdbc.exceptions.ConnectionFeatureNotAvailableException;

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
    /**
     * 数据源设置
     */
    private static DataSource dataSource;

    /**
     * 设置ThreadLocal容器确保同一个线程，获取同一份数据
     */
    private static final ThreadLocal<Connection> THREAD_LOCAL_CONNECTION = new ThreadLocal<>();


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
        System.out.println("invoke getDataSource" + dataSource.getClass());
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        System.out.println(Thread.currentThread().getName() + "当前处理请求的线程名");

        Connection connection = THREAD_LOCAL_CONNECTION.get();

        if (connection == null) {
            THREAD_LOCAL_CONNECTION.set(getDataSource().getConnection());
            connection = THREAD_LOCAL_CONNECTION.get();
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
