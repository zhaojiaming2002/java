package net.microsoft.java.web.dao.impl;

import net.microsoft.java.web.dao.UserDao;
import net.microsoft.java.web.entity.User;
import net.microsoft.java.web.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:UserDao的实现类
 * @Date on 2022/4/13
 * @author:Suche
 **/

public class PreparedStatementUserDaoImpl implements UserDao {
    @Override
    public int insert(User user) {
        String sql = null;
        if (null != user && user.getName() != null && user.getPassword() != null) {
            sql = "insert into jdbc_user values(null,?,?,now(),now())";


            try (
                    Connection connection = JDBCUtil.getConnection();
                    final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                final int row = preparedStatement.executeUpdate();
                return row;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    @Override
    public int delete(User user) {
        String sql = null;
        if (null != user && user.getId() != null) {
            sql = "delete from jdbc_user where id = ?";
        } else if (null != user && user.getName() != null && user.getName() != "") {
            sql = "delete from jdbc_user where name = ?";
        }
        try (
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            if (null != user && null != user.getId() && null != sql) {
                preparedStatement.setInt(1, user.getId());
            } else if (null != user && null != user.getName() && null != sql) {
                preparedStatement.setString(1, user.getName());
            }
            final int row = preparedStatement.executeUpdate();
            return row;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }


    @Override
    public int update(User user) {
        String sql = null;
        if (user.getId() != null && user != null && null != user.getPassword()) {
            sql = "update jdbc_user set password = ? where id = ?";
        } else if (user.getName() != null && user != null && null != user.getPassword()) {
            sql = "update jdbc_user set password = ? where name = ?";
        }

        try (
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ) {
            if (sql != null && sql != "") {
                if (null != user && null != user.getId() && null != user.getPassword()) {
                    preparedStatement.setString(1, user.getPassword());
                    preparedStatement.setInt(2, user.getId());
                } else if (null != user && null != user.getName() && null != user.getPassword()) {
                    preparedStatement.setString(1, user.getPassword());
                    preparedStatement.setString(2, user.getName());
                }
                final int row = preparedStatement.executeUpdate();
                return row;


            }
            return 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> select(User userCondition) {
        String sql = null;
        if (userCondition == null) {
            sql = "select id,name,password,create_date,update_date from jdbc_user where 1=1";

        } else if (null != userCondition && userCondition.getName() != null && userCondition.getName() != "" && userCondition.getPassword() != null && userCondition.getPassword() != "") {
            sql = "select id,name,password,create_date,update_date from jdbc_user where name = ? and password = ?";

        } else if (null != userCondition && userCondition.getId() != null) {

            sql = "select id,name,password,create_date,update_date from jdbc_user where id = ?";

        } else if (null != userCondition && userCondition.getName() != null && userCondition.getName() != "") {

            sql = "select id,name,password,create_date,update_date from jdbc_user where name = ?";


        }

        if (sql != null && sql != "") {

            try (
                    final Connection connection = JDBCUtil.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {

                if (null != userCondition && userCondition.getName() != null) {
                    preparedStatement.setString(1, userCondition.getName());
                } else if (null != userCondition && userCondition.getId() != null) {
                    preparedStatement.setInt(1, userCondition.getId());
                } else if (null != userCondition && null != userCondition.getName() && null != userCondition.getPassword()) {
                    preparedStatement.setString(1, userCondition.getName());
                    preparedStatement.setString(2, userCondition.getPassword());
                }

                ResultSet resultSet = preparedStatement.executeQuery();
                List<User> userList = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    LocalDateTime createDate = resultSet.getTimestamp("create_date").toLocalDateTime();
                    LocalDateTime updateDate = resultSet.getTimestamp("update_date").toLocalDateTime();
                    User user = new User(id, name, password, createDate, updateDate);
                    userList.add(user);
                }

                return userList;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;

    }

    @Override
    public long count() {
        String sql = null;
        sql = "select count(*) from jdbc_user";
        try (
                final Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }


}
