package net.microsoft.java.web.service;

import net.microsoft.java.web.bean.entity.User;

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
    boolean login(User user) throws Exception;


    /**
     * 注册
     *
     * @param user
     * @return
     */
    boolean register(User user) throws Exception;

    /**
     * 获取所有用户
     *
     * @return
     */
    public List<User> findAllUsers() throws Exception;


}
