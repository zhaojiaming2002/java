package net.microsoft.java.web.foundational.entity;

import java.sql.Timestamp;

/**
 * @description:用户实体类
 * @Date on 2022/4/11
 * @author:Suche
 **/

public class User {

    private Integer id;
    private String name;
    private String password;
    private Timestamp createDate;
    private Timestamp upDateDate;

    public User() {
    }

    public User(Integer id, String name, String password, Timestamp createDate, Timestamp upDateDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.createDate = createDate;
        this.upDateDate = upDateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpDateDate() {
        return upDateDate;
    }

    public void setUpDateDate(Timestamp upDateDate) {
        this.upDateDate = upDateDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", upDateDate=" + upDateDate +
                '}';
    }
}
