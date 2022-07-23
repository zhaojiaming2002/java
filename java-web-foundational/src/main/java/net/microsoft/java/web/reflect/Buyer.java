package net.microsoft.java.web.reflect;

/**
 * @description: 代理者
 * @Date on 2022/6/20
 * @author: suche
 **/

public class Buyer implements Shopping {
    private final User user;

    public Buyer(User user) {
        this.user = user;
    }

    @Override
    public void buyCellPhone() {
        this.user.sendCode();
        System.out.println("登录商城成功");
        System.out.println("抢到手机");
        this.user.buyCellPhone();
    }

    @Override
    public void test2() {

    }
}
