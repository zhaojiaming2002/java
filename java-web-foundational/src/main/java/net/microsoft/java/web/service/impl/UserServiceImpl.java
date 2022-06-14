package net.microsoft.java.web.service.impl;

import net.microsoft.java.web.dao.UserDao;
import net.microsoft.java.web.dao.impl.CustomerQueryRunnerUserDaoImpl;
import net.microsoft.java.web.dao.impl.PreparedStatementUserDaoImpl;
import net.microsoft.java.web.dao.impl.QueryRunnerUserDaoImpl;
import net.microsoft.java.web.entity.User;
import net.microsoft.java.web.service.UserService;

import java.util.List;

/**
 * @description: User业务逻辑层实现
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class UserServiceImpl implements UserService {
    UserDao userDao = new CustomerQueryRunnerUserDaoImpl();

    /**
     * Service 层登录
     *
     * @param user
     * @return
     */
    @Override
    public boolean login(User user) {
        if (null != user && null != user.getName() && user.getName() != "") {
            if (null != user.getPassword() && user.getPassword() != "") {
                List<User> userList = userDao.select(user);
                if (null != userList && userList.size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Service 层注册
     *
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        if (null != user && null != user.getName() && user.getName() != "" && user.getPassword() != null && user.getPassword() != "") {
            int row = userDao.insert(user);
            return row == 1 ? true : false;
        }
        return false;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = userDao.select(null);
        if (null != userList && userList.size() > 0) {
            return userList;
        }
        return null;
    }
}
