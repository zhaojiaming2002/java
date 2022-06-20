package net.microsoft.java.web.service;

import net.microsoft.java.web.entity.User;

import java.util.List;

/**
 * @description:User业务逻辑层
 * @Date on 2022/4/16
 * @author:Suche
 **/

public interface UserService {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    boolean login(User user);


    /**
     * 注册
     *
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 获取所有用户
     *
     * @return
     */
    public List<User> findAllUsers();


}
