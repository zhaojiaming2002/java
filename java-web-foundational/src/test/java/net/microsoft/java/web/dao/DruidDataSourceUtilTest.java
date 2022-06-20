package net.microsoft.java.web.dao;

import net.microsoft.java.web.util.DruidDataSourceUtil;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @description: Druid工具类测试
 * @Date on 2022/6/19
 * @author: suche
 **/

public class DruidDataSourceUtilTest {

    @Test
    public void testDruidDataSourceUtil() {

        try {
            System.out.println(DruidDataSourceUtil.getConnection());
            System.out.println(DruidDataSourceUtil.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

            threadLocal.set(DruidDataSourceUtil.getConnection());
            System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());


            new Thread(() -> {
                try {
                    threadLocal.set(DruidDataSourceUtil.getConnection());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());
            }).start();
            System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
