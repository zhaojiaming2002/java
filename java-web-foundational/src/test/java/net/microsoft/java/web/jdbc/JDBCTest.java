package net.microsoft.java.web.jdbc;

import net.microsoft.java.web.foundational.dao.Dao;
import net.microsoft.java.web.foundational.dao.impl.StatementDaoImplV1;
import net.microsoft.java.web.foundational.entity.User;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.List;


/**
 * @description:JDBC编程
 * @Date on 2022/4/9
 * @author:Suche
 **/

public class JDBCTest {
    /**
     * 使用jdbc查询数据库
     */
    @Test
    public void testSelectJDBCUserTableData() throws SQLException, ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/jdbc";
        String userName = "root";
        String userPassword = "123456";
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得连接
        Connection connection = DriverManager.getConnection(url, userName, userPassword);
        //3.获得执行SQL的对象
        Statement statement = connection.createStatement();
        String sql = "select * from jdbc_user";
        //4.执行SQL获取返回结果
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println(resultSet.getClass());
        //5.处理结果
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            Timestamp create_date = resultSet.getTimestamp("create_date");
            Timestamp update_date = resultSet.getTimestamp("update_date");
            System.out.print("id:" + id + " " + "name:" + name + "password:" + password + "create_date:" + create_date + "update_date:" + update_date);
            System.out.println();
        }
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }

    }

    /**
     * TryWithResource关闭资源
     */
    @Test
    public void testSelectJDBCUserTableDataTryWithResource() {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String userName = "root";
        String userPassword = "123456";

        try (
                Connection connection = DriverManager.getConnection(url, userName, userPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from jdbc_user");

        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                Timestamp create_date = resultSet.getTimestamp("create_date");
                Timestamp update_date = resultSet.getTimestamp("update_date");
                System.out.print("id:" + id + " " + "name:" + name + "password:" + password + "create_date:" + create_date + "update_date:" + update_date);
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testClassLoader() {
        Class<JDBCTest> jdbcTestClass = JDBCTest.class;
        System.out.println(jdbcTestClass);
    }

    @Test
    public void testStatementDaoInsertImpl() {
        Dao dao = new StatementDaoImplV1();
        User user = new User();
        user.setName("Kez");
        user.setPassword("1111111");
        dao.insert(user);

    }

    @Test
    public void testStatementDaoDeleteImpl() {
        Dao dao = new StatementDaoImplV1();
        User user = new User();
        user.setName("Kez");
        System.out.println(dao.delete(user));
    }

    @Test
    public void testStatementSelectImpl() {
        Dao dao = new StatementDaoImplV1();
        User userd = new User();
        userd.setId(1);
        final List<User> selectUser = dao.select(userd);
        for (User user : selectUser) {
            System.out.println(user);
        }
    }
}
