package net.microsoft.java.web.foundational.jdbc;

import net.microsoft.java.web.foundational.dao.Dao;
import net.microsoft.java.web.foundational.dao.impl.StatementDaoImplV2;
import net.microsoft.java.web.foundational.entity.User;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @description:
 * @Date on 2022/4/11
 * @author:Suche
 **/

public class DaoTest {
    public static void main(String[] args) {
        User user = new User(1, "小明", "123456", new Timestamp(LocalDate.EPOCH.toEpochDay()), new Timestamp(LocalDate.EPOCH.toEpochDay()));
        Dao dao = new StatementDaoImplV2();
        dao.insert(user);

    }

    @Test
    public void testUserConstructor() {
    }
}
