package net.microsoft.java.web.service;

import net.microsoft.java.web.entity.User;

/**
 * @description:User业务逻辑层
 * @Date on 2022/4/16
 * @author:Suche
 **/

public interface UserService {

    boolean login(User user);
}
