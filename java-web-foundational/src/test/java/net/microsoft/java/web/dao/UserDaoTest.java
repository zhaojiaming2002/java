package net.microsoft.java.web.dao;

import net.microsoft.java.web.dao.impl.CustomerQueryRunnerUserDaoImpl;
import net.microsoft.java.web.dao.impl.PreparedStatementUserDaoImpl;
import net.microsoft.java.web.dao.impl.QueryRunnerUserDaoImpl;
import net.microsoft.java.web.dao.impl.StatementUserDaoImplV1;
import net.microsoft.java.web.entity.User;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @description:
 * @Date on 2022/4/11
 * @author:Suche
 **/

public class UserDaoTest {
    static UserDao userDao = new CustomerQueryRunnerUserDaoImpl();


    @Test
    public void testUserConstructor() {
    }


    @Test
    public void testInsert() {
        User user = new User(null, "李瑞华", "123456", LocalDateTime.now(), LocalDateTime.now());
        int insert = userDao.insert(user);
        System.out.println(insert == 1 ? "添加成功" : "添加失败");

    }


    @Test
    public void testSelect() {
//        User user = new User();
//        user.setId(12);
//        List<User> selectUsers = userDao.select(user);
//
//        for (User selectUser : selectUsers) {
//            System.out.println(selectUser);
//        }
        User user = new User();
        final List<User> select = userDao.select(user);
        System.out.println(select);
//        user.setId(1);
//        List<User> userList = userDao.select(user);
//        System.out.println(userList);

    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setName("李瑞华");

        int insert = userDao.delete(user);

        System.out.println(insert);

    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1);
        user.setPassword("7777777");
        int row = userDao.update(user);
        System.out.println(row);

    }

    @Test
    public void testCount() {
        System.out.println("userDao.count() = " + userDao.count());

    }

    @Test
    public void testDD() {
        final Class<Integer> integerClass = Integer.class;
        try {
            Integer integer = integerClass.getConstructor().newInstance("12");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
