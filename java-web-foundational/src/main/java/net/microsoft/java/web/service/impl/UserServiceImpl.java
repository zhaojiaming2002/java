package net.microsoft.java.web.service.impl;

import net.microsoft.java.web.dao.UserDao;
import net.microsoft.java.web.dao.impl.PreparedStatementUserDaoImpl;
import net.microsoft.java.web.entity.User;
import net.microsoft.java.web.service.UserService;

import java.util.List;

/**
 * @description: User业务逻辑层实现
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class UserServiceImpl implements UserService {
    UserDao userDao = new PreparedStatementUserDaoImpl();

    @Override
    public boolean login(User user) {
        if (null != user && null != user.getName() && user.getName() != "") {
            if (null != user.getPassword() && user.getPassword() != "") {
                List<User> select = userDao.select(user);

                return select.size() == 1;
            }
        }
        return false;
    }
}
