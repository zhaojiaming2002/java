package net.microsoft.java.web.dao;

import net.microsoft.java.web.dao.impl.PreparedStatementUserDaoImpl;
import net.microsoft.java.web.foundational.entity.User;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * @description:
 * @Date on 2022/4/11
 * @author:Suche
 **/

public class UserDaoTest {
    static UserDao userDao = new PreparedStatementUserDaoImpl();


    @Test
    public void testUserConstructor() {
    }


    @Test
    public void testInsert() {
        User user = new User(null, "李瑞华", "123456", new Timestamp(LocalDate.EPOCH.toEpochDay()), new Timestamp(LocalDate.EPOCH.toEpochDay()));
        int insert = userDao.insert(user);
        System.out.println(insert == 1 ? "添加成功" : "添加失败");

    }


    @Test
    public void testSelect() {
        User user = new User();
        user.setId(12);
        List<User> selectUsers = userDao.select(user);

        for (User selectUser : selectUsers) {
            System.out.println(selectUser);
        }

    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setName("小明");

        int insert = userDao.delete(user);


    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setName("李瑞华");
        user.setPassword("7777777");
        int row = userDao.update(user);


    }
}
