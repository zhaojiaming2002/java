package net.microsoft.java.web.dao.impl;

import net.microsoft.java.web.dao.UserDao;
import net.microsoft.java.web.entity.User;
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
    public int insert(User user) {
        String sql = null;
        if (null != user) {
            sql = "insert into jdbc_user values(null,?,?,now(),now())";
            int row = customerQueryRunner.update(sql, user.getName(), user.getPassword());
            return row;
        }
        return 0;
    }

    @Override
    public int delete(User user) {
        return 0;
    }

    @Override
    public List<User> select(User user) {
        String sql = "select id,name,password,create_date,update_date from jdbc_user";

        List<User> userList = customerQueryRunner.queryForBeans(sql, User.class);
        if (null != user && null != user.getId()) {
            customerQueryRunner.queryForBeans(sql, User.class);
        }

        return userList;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public long count() {
        String sql = "select count(*) from jdbc_user";
//        long count = customerQueryRunner.queryForLong(sql, 1);
        Long count = customerQueryRunner.queryForType(sql, Long.class);

        return count;
    }
}
