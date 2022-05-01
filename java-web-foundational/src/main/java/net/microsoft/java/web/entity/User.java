package net.microsoft.java.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @description:用户实体类
 * @Date on 2022/4/11
 * @author:Suche
 **/

public class User implements Serializable {

    private Integer id;
    private String name;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime upDateDate;

    public User() {
    }

    public User(Integer id, String name, String password, LocalDateTime createDate, LocalDateTime upDateDate) {
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return upDateDate;
    }

    public void setUpdateDate(LocalDateTime upDateDate) {
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
