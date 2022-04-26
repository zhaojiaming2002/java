package net.microsoft.java.web.util;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @description: 自定义连接池实现-基础版
 * @Date on 2022/4/19
 * @author:Suche
 **/

public class CustomerDataSourceV1 {
    private static final LinkedList<Connection> connectionPool = new LinkedList<>();

    static {
        for (int i = 0; i < 5; i++) {
            Connection connection = JDBCUtil.getConnection();
            connectionPool.add(connection);
        }

        System.out.println("当前连接池数量" + connectionPool.size());
    }


    public Connection getConnection() {
        Connection connection = null;
        if (connectionPool.size() > 0) {
            connection = connectionPool.removeFirst();
            System.out.println("获取连接后，连接池数量为" + connectionPool.size());

            return connection;
        } else {
            connection = JDBCUtil.getConnection();
            return connection;
        }

    }

    public void giveBackConnection(Connection connection) {
        connectionPool.addLast(connection);
        System.out.println("归还连接后，连接池数量为" + connectionPool.size());


    }
}
