package net.microsoft.java.reflect;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

/**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

public class ProxyUseAgeTest {


    public static void main(String[] args) {
        User user = new User();
        // 要被代理的ClassLoader 被代理的接口 增强方法
        UserCommon o = (UserCommon) Proxy.newProxyInstance(User.class.getClassLoader(), new Class[]{UserCommon.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String proxyMethodName = "getName";
                if (method.getName().equals(proxyMethodName)) {
                    String resultValue = (String) method.invoke(user, args);
                    return resultValue + "!!!!";
                }
                return method.invoke(user, args);
            }
        });

        o.setName("hello");
        System.out.println(o.getName());

    }


}

interface UserCommon {

    String getName();

    void setName(String name);
}

class User implements UserCommon {
    String name;

    @Override

    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}

