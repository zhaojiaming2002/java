package net.microsoft.java.reflect;

import net.microsoft.java.web.reflect.Buyer;
import net.microsoft.java.web.reflect.Shopping;
import net.microsoft.java.web.reflect.User;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

public class ProxyTest {

    @Test
    public void staticProxy() {
        // 被代理者
        User user = new User();
        // 代理者
        Buyer buyer = new Buyer(user);

        // 调用代理者方法
        buyer.buyCellPhone();
    }

    @Test
    public void dynamicProxyWithOutArgsWithOutReturnValue() throws Exception {
        // 被代理者
        User user = new User();

        // 代理者
        Shopping o = (Shopping) Proxy.newProxyInstance(User.class.getClassLoader(), User.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("buyCellPhone")) {
                    user.sendCode();
                    method.invoke(user, args);
                }
                return null;
            }
        });
        o.buyCellPhone();
    }

    @Test
    public void dynamicProxyArgsReturnValue() throws Exception {
        // 被代理者
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(100, 100, 200, 300));

        System.out.println(arrayList);

        // 代理者
        List<Integer> o = (List<Integer>) Proxy.newProxyInstance(ArrayList.class.getClassLoader(), ArrayList.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName());
                if (method.getName().equals("remove")) {
                    Iterator<Integer> iterator = arrayList.iterator();
                    while (iterator.hasNext()) {
                        Integer next = iterator.next();
                        if (next.equals(args[0])) {
                            iterator.remove();
                        }
                    }
                    return args[0];
                } else {
                    return method.invoke(arrayList, args);
                }

            }
        });

        Integer remove = o.remove(100);
        System.out.println(remove);


        for (Integer integer : o) {
            System.out.println(integer);
        }


    }
}
