package net.microsoft.java.web.servlet;
/**
 * @description:获取Cookie的值
 * @Date on 2022/6/4
 * @author: suche
 **/

import net.microsoft.java.web.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/cookie/get")
public class CookieGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookieName = "courseName";

/*        // 获取浏览器内存储的Cookie
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    System.out.println(cookieName + "---->" + cookie.getValue());
                }
            }
        }*/

        CookieUtil.getCookie(cookieName, request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
