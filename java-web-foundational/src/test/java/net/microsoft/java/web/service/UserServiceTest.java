package net.microsoft.java.web.service;

import net.microsoft.java.web.entity.User;
import net.microsoft.java.web.service.impl.UserServiceImpl;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @description:UserService测试
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void testLogin() {
        User user = new User();
        user.setName("李瑞华");
        user.setPassword("7777777");
        boolean login = userService.login(user);
        System.out.println(login ? "登陆成功" : "登陆失败");
    }


    @Test
    public void testRegister() {
        User user = new User();
        user.setName("李瑞华");
        user.setPassword("7777777");
        final boolean register = userService.register(user);
        System.out.println(register ? "注册成功" : "注册失败");
    }

    @Test
    public void testFinaAllUsers() {
        List<User> allUsers = userService.findAllUsers();
        System.out.println(allUsers);
    }
}

