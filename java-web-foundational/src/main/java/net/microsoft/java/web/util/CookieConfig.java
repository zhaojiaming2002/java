package net.microsoft.java.web.util;

/**
 * @description: CookieConfig封装
 * @Date on 2022/6/4
 * @author: suche
 **/

public class CookieConfig {
    /**
     * Cookie的名
     */
    private String CookieName;

    /**
     * 设置Cookie的值
     */
    private String CookieValue;


    /**
     * 配置Cookie的过期时间
     */
    private int maxAge = 7 * 24 * 60 * 60;


    /**
     * 设置Cookie有效路径
     */
    private String path;

    public String getCookieName() {
        return CookieName;
    }

    public void setCookieName(String cookieName) {
        CookieName = cookieName;
    }

    public String getCookieValue() {
        return CookieValue;
    }

    public void setCookieValue(String cookieValue) {
        CookieValue = cookieValue;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
