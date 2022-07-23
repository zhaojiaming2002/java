package net.microsoft.java.web.reflect;

/**
 * @description: 被代理者
 * @Date on 2022/6/20
 * @author: suche
 **/

public class User implements Shopping {
    @Override
    public void buyCellPhone() {
        System.out.println("普通用户:购买手机成功");
    }

    @Override
    public void test2() {

    }


    /**
     * 发送验证码
     */
    public void sendCode() {
        System.out.println("普通用户:验证码为66666");
    }


}
