package net.microsoft.java.reflect;

import java.lang.reflect.Method;

/**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

public class testMethodInvoke {
    public static void main(String[] args) {
        B b = new B();
        b.get();

    }


}

class A {
    public void get() {
        Method[] methods = this.getClass().getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

    }

}

class B extends A {


    public void set() {

    }
}