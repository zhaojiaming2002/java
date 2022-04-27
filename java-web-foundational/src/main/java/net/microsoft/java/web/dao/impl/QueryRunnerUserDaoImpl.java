package net.microsoft.java.web.dao.impl;

import net.microsoft.java.web.dao.UserDao;
import net.microsoft.java.web.entity.User;
import net.microsoft.java.web.util.DruidDataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Date on 2022/4/26
 * @author: suche
 **/

public class QueryRunnerUserDaoImpl implements UserDao {

    QueryRunner queryRunner = new QueryRunner(DruidDataSourceUtil.getDataSource());


    @Override
    public int insert(User user) {
        String sql = null;
        if (null != user && null != user.getName() && null != user.getPassword()) {
            sql = "insert into jdbc_user values(null,?,?,now(),now())";
            try {
                int row = queryRunner.update(sql, user.getName(), user.getPassword());
                return row;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return 0;
    }

    @Override
    public int delete(User user) {
        String sql = null;
        try {
            int row = 0;
            if (null != user && null != user.getId()) {
                sql = "delete from jdbc_user where id = ?";
                row = queryRunner.update(sql, user.getId());
                return row;
            } else if (null != user && null != user.getName()) {
                sql = "delete  from jdbc_user where name = ?";
                row = queryRunner.update(sql, user.getName());
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
        if (null == user) {
            sql = "select id,name,password,create_date,update_date from jdbc_user where 1=1";
            try {
                List<User> userList = queryRunner.query(sql, new BeanListHandler<User>(User.class));

                return userList;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        if (null != user && null != user.getId()) {
            sql = "select id,name,password,create_date,update_date from jdbc_user where id = ?";
            try {
                List<User> userList = new ArrayList<>();

                userList.add(queryRunner.query(sql, new BeanHandler<>(User.class), user.getId()));
                return userList;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


        return null;


    }


    @Override
    public int count() {
        String sql = "select count(*) from jdbc_user";
        try {
            Long query = queryRunner.query(sql, new ScalarHandler<Long>(1));
            return query.intValue();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    @Override
    public int update(User user) {
        String sql = null;
        if (null != user && null != user.getId() && null != user.getPassword()) {
            sql = "update jdbc_user set password = ? where id = ? ";

            try {
                int row = queryRunner.update(sql, user.getPassword(), user.getId());
                return row;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return 0;
    }
}
