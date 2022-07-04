package net.microsoft.java.web.util;

import net.microsoft.java.web.bean.entity.User;
import org.testng.annotations.Test;

/**
 * @description: CustomerBeanUtils测试
 * @Date on 2022/6/3
 * @author: suche
 **/

public class CustomerBeanUtilsTest {


    @Test
    public void testPopulate() {
        CustomerBeanUtils.populate(new User(), null);
    }
}
