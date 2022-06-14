package net.microsoft.java.web.bean.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description: View Object
 * @Date on 2022/6/14
 * @author: suche
 **/

public class UserVO implements Serializable {

    private Integer id;
    private String name;
    private String password;
    private String createDate;
    private String upDateDate;

    public UserVO() {
    }

    public UserVO(Integer id, String name, String password, String createDate, String upDateDate) {
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpDateDate() {
        return upDateDate;
    }

    public void setUpDateDate(String upDateDate) {
        this.upDateDate = upDateDate;
    }
}
