package net.microsoft.java.web.foundational.dao;

import net.microsoft.java.web.foundational.entity.User;

import java.util.List;

/**
 * @description:Dao数据访问接口
 * @Date on 2022/4/11
 * @author:Suche
 **/

public interface Dao {
    String url = "jdbc:mysql://localhost:3306/jdbc";
    String userName = "root";
    String userPassword = "123456";

    int insert(User user);


    int delete(User user);

    List<User> select(User user);


}
