package net.microsoft.java.web.dao;

import net.microsoft.java.web.entity.User;

import java.util.List;

/**
 * @description:Dao数据访问接口
 * @Date on 2022/4/11
 * @author:Suche
 **/

public interface UserDao {


    int insert(User user);


    int delete(User user);

    List<User> select(User user);

    int update(User user);


    long count();

}
