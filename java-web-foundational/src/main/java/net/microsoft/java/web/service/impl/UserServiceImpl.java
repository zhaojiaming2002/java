package net.microsoft.java.web.service.impl;

import net.microsoft.java.web.dao.UserDao;
import net.microsoft.java.web.dao.impl.CustomerQueryRunnerUserDaoImpl;
import net.microsoft.java.web.bean.entity.User;
import net.microsoft.java.web.exception.BusinessException;
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
    public boolean login(User user) throws Exception {
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
    public boolean register(User user) throws Exception {
        if (null != user && null != user.getName() && user.getName() != "" && user.getPassword() != null && user.getPassword() != "") {
            int row = userDao.insert(user);
            return row == 1 ? true : false;
        }
        return false;
    }

    @Override
    public List<User> findAllUsers() throws Exception {
        List<User> userList = userDao.select(null);
        if (null != userList && userList.size() > 0) {
            return userList;
        }
        return null;
    }

    @Override
    public boolean checkName(String name) {
        if (null != name && name != "") {
            User user = new User(name);
            try {
                List<User> selectUserResult = userDao.select(user);
                if (selectUserResult != null) {
                    throw new BusinessException("用户已经存在");
                } else {
                    return true;
                }
            } catch (BusinessException e) {
                throw new BusinessException(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("服务器错误");
            }
        }
        return false;

    }
}
