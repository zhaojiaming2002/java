package net.microsoft.java.web.util;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * @description: 资源池V2
 * @Date on 2022/4/22
 * @author:Suche
 **/

public class CustomerDataSourceV2 implements DataSource {
    private final static LinkedList<Connection> connectionPool;

    static {
        connectionPool = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Connection connection = JDBCUtil.getConnection();
            connectionPool.add(connection);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        // 如果池中有可用连接
        if (connectionPool.size() > 0) {
            // 直接返回
            connection = connectionPool.removeFirst();
            // 创建Connection装饰类对象
            ConnectionDecorate connectionDecorate = new ConnectionDecorate(connection, connectionPool);
            System.out.println("【高级版】从连接池中获取连接之后当前连接池可用的连接数量是" + connectionPool.size());
            // 此时返回的是增强的连接，就是将有原有的Connection的close()方法改成归还连接
            return connectionDecorate;
        } else {
            connection = JDBCUtil.getConnection();
            return connection;
        }

    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
