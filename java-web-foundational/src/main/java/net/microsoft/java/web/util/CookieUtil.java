package net.microsoft.java.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:CookieUtil的使用
 * @Date on 2022/6/4
 * @author: suche
 **/

public class CookieUtil {


    /**
     * 添加Cookie
     *
     * @param cookieConfig
     * @param response
     */
    public static void addCookie(CookieConfig cookieConfig, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieConfig.getCookieName(), cookieConfig.getCookieValue());
        cookie.setMaxAge(cookieConfig.getMaxAge());
        cookie.setPath(cookieConfig.getPath());

        response.addCookie(cookie);
    }

    /**
     * 获取Cookie
     *
     * @param cookieName
     * @param request
     */
    public static Cookie getCookie(String cookieName, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;

    }

    /**
     * 删除Cookie
     *
     * @param cookieConfig
     * @param response
     */
    public static void removeCookie(CookieConfig cookieConfig, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieConfig.getCookieName(), cookieConfig.getCookieValue());
        cookie.setPath(cookieConfig.getPath());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
