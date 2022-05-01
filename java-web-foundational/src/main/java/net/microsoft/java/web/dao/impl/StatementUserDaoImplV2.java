package net.microsoft.java.web.dao.impl;

import net.microsoft.java.web.dao.UserDao;
import net.microsoft.java.web.entity.User;
import net.microsoft.java.web.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:Dao impl
 * @Date on 2022/4/11
 * @author:Suche
 **/

public class StatementUserDaoImplV2 implements UserDao {

    static String quotes = "'";
    static String comma = ",";
    static Connection connection;

    static {
        connection = JDBCUtil.getConnection();
    }

    @Override
    public int insert(User user) {

        String sql = "insert into jdbc_user values(null," + quotes + user.getName() + quotes + comma + quotes + user.getPassword() + quotes + comma + "now(),now()" + ")";
        final Statement statement;
        try {

            if (sql != null && sql != "") {
                statement = connection.createStatement();
                final int row = statement.executeUpdate(sql);
                return row;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(User user) {
        String sql = null;
        try (
                Connection connection = JDBCUtil.getConnection();
                Statement statement = connection.createStatement()

        ) {
            if (sql != null && sql != "") {
                if (user.getId() != null && user != null) {
                    sql = "delete from jdbc_user where id = " + quotes + user.getId() + quotes;
                    System.out.println("sql = " + sql);
                    if (null != sql && sql != "") {
                        int row = statement.executeUpdate(sql);
                        return row;
                    }
                }
            } else if (null != user && user.getName() != null) {
                sql = "delete from jdbc_user where name = " + quotes + user.getName() + quotes;
                System.out.println("sql = " + sql);
                int row = statement.executeUpdate(sql);
                return row;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<User> select(User user) {
        String sql = null;
        List<User> userList = new ArrayList<>();
        try (
                final Connection connection = JDBCUtil.getConnection();
                final Statement statement = connection.createStatement()
        ) {
            if (user == null) {
                sql = "select id,name,password,create_date,update_date from jdbc_user";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    final int id = resultSet.getInt("id");
                    final String name = resultSet.getString("name");
                    final String password = resultSet.getString("password");
                    final LocalDateTime createDate = resultSet.getTimestamp("create_date").toLocalDateTime();
                    final LocalDateTime updateDate = resultSet.getTimestamp("update_date").toLocalDateTime();
                    userList.add(new User(id, name, password, createDate, updateDate));
                }
                return userList;
            } else if (null != user && user.getId() != null) {
                sql = "select id,name,password,create_date,update_date from jdbc_user where id =" + quotes + user.getId() + quotes;
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    final int id = resultSet.getInt("id");
                    final String name = resultSet.getString("name");
                    final String password = resultSet.getString("password");
                    final LocalDateTime createDate = resultSet.getTimestamp("create_date").toLocalDateTime();
                    final LocalDateTime updateDate = resultSet.getTimestamp("update_date").toLocalDateTime();
                    userList.add(new User(id, name, password, createDate, updateDate));
                    return userList;
                }
            } else if (null != user && user.getName() != null) {
                sql = "select id,name,password,create_date,update_date from jdbc_user where name =" + quotes + user.getName() + quotes;
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    final int id = resultSet.getInt("id");
                    final String name = resultSet.getString("name");
                    final String password = resultSet.getString("password");
                    final LocalDateTime createDate = resultSet.getTimestamp("create_date").toLocalDateTime();
                    final LocalDateTime updateDate = resultSet.getTimestamp("update_date").toLocalDateTime();
                    userList.add(new User(id, name, password, createDate, updateDate));
                }
                return userList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public long count() {
        String sql = "select count(*) from jdbc_user";
        try (
                final Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            if (null != sql) {
                final ResultSet resultSet = preparedStatement.executeQuery(sql);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }
}


