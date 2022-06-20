package net.microsoft.java.lang;

import org.testng.annotations.Test;

/**
 * @description: ThreadLocal使用测试
 * @Date on 2022/6/19
 * @author: suche
 **/

public class ThreadLocalUsageTest {

    /**
     * ThreadLocal的作用
     * ThreadLocal是一个容器
     * 他的作用保证在同一个线程中使用的对象相同，同一个线程中只能存放一个数据，这样同一个线程获取的时候取同一个值
     */
    @Test
    public void testThreadLocal() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        threadLocal.set("Java");
        System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());

        new Thread(() -> {
            threadLocal.set("C++");
            System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());
        }).start();
        System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());


    }
}
