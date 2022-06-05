package net.microsoft.java.web.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @description:自己实现BeanUtils部分功能
 * @Date on 2022/6/3
 * @author: suche
 **/

public class CustomerBeanUtils {

    public static void populate(final Object bean, final Map<String, ? extends Object> properties) {
        Class<?> beanClass = bean.getClass();
        Method[] methods = beanClass.getMethods();
        Stream<Method> stream = Arrays.stream(methods);
        stream.filter((name) -> {
            return name.getName().startsWith("set");
        }).forEach((name) -> {
            System.out.println(name);
        });


    }


}
