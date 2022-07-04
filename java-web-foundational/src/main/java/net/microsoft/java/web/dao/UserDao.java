package net.microsoft.java.web.dao;

import net.microsoft.java.web.bean.entity.User;

import java.util.List;

/**
 * @description:Dao数据访问接口
 * @Date on 2022/4/11
 * @author:Suche
 **/

public interface UserDao {


    int insert(User user) throws Exception;


    int delete(User user) throws Exception;

    List<User> select(User user) throws Exception;

    int update(User user) throws Exception;


    long count() throws Exception;

}
