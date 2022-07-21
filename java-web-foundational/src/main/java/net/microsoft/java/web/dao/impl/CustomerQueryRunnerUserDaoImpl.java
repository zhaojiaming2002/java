package net.microsoft.java.web.dao.impl;

import net.microsoft.java.web.dao.UserDao;
import net.microsoft.java.web.bean.entity.User;
import net.microsoft.java.web.util.CustomerQueryRunner;
import net.microsoft.java.web.util.DruidDataSourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Date on 2022/4/28
 * @author: suche
 **/

public class CustomerQueryRunnerUserDaoImpl implements UserDao {

    CustomerQueryRunner customerQueryRunner = new CustomerQueryRunner(DruidDataSourceUtil.getDataSource());


    @Override
    public int insert(User user) throws Exception {
        String sql = null;
        if (null != user) {
            sql = "insert into jdbc_user values(null,?,?,now(),now())";
            int row = customerQueryRunner.update(sql, user.getName(), user.getPassword());
            return row;
        }
        return 0;
    }

    @Override
    public int delete(User user) throws Exception {
        String sql = null;
        if (null != user && user.getId() != null) {
            sql = "delete from jdbc_user where id=?";
            int row = customerQueryRunner.update(sql, user.getId());
            return row;
        }
        return 0;
    }

    @Override
    public List<User> select(User user) throws Exception {
        if (user == null) {
            String sql = "select id,name,password,create_date,update_date from jdbc_user";
            List<User> userList = customerQueryRunner.queryForBeans(sql, User.class);
            if (null != userList && userList.size() > 0) {
                return userList;
            }
        }

        if (null != user && user.getName() != null && user.getPassword() != null) {
            String sql = "select id,name,password,create_date,update_date from jdbc_user where name = ? and password = ?";
            List<User> userList = customerQueryRunner.queryForBeans(sql, User.class, user.getName(), user.getPassword());
            if (null != userList && userList.size() > 0) {
                return userList;
            }
        }
        if (user != null && user.getName() != null && user.getName() != "") {
            String sql = "select id,name,password,create_date,update_date from jdbc_user where name = ?";
            User queryResult = customerQueryRunner.queryForBean(sql, User.class, user.getName());
            if (queryResult != null) {
                List<User> userList = new ArrayList<>();
                userList.add(queryResult);
                return userList;
            }
        }
        return null;
    }

    @Override
    public int update(User user) throws Exception {
        if (null != user && user.getId() != null && user.getPassword() != null) {
            String sql = "update jdbc_user set password = ? where id = ? ";
            int row = customerQueryRunner.update(sql, user.getPassword(), user.getId());
            return row;
        }
        return 0;
    }

    @Override
    public long count() throws Exception {
        String sql = "select count(*) from jdbc_user";
//        long count = customerQueryRunner.queryForLong(sql, 1);
        Long count = customerQueryRunner.queryForType(sql, Long.class);

        return count;
    }
}
