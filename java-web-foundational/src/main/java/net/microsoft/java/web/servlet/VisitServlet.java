package net.microsoft.java.web.servlet; /**
 * @description:实现记住用户上次访问时间
 * @Date on 2022/6/4
 * @author: suche
 **/

import net.microsoft.java.web.util.CookieConfig;
import net.microsoft.java.web.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/visit")
public class VisitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookieName = "visitTime";
        Cookie cookie = CookieUtil.getCookie(cookieName, request);
        String responseText = "";
        response.setContentType("text/html; charset=UTF-8");
        if (cookie == null) {
            responseText = "你是第一次访问网站";
        } else {
            responseText = "你上次访问时间为" + cookie.getValue();
        }
        CookieConfig cookieConfig = new CookieConfig();
        cookieConfig.setCookieName(cookieName);
        cookieConfig.setCookieValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd/hh-mm-ss")));
        cookieConfig.setPath(request.getContextPath());
        CookieUtil.addCookie(cookieConfig, response);
        response.getWriter().write(responseText);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
