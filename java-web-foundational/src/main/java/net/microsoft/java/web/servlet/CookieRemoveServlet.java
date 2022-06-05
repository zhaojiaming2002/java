package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/6/4
 * @author: suche
 **/

import net.microsoft.java.web.util.CookieConfig;
import net.microsoft.java.web.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/cookie/remove")
public class CookieRemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/*
        String cookieName = "courseName";
        String cookieValue = "Java";
        // 创建Cookie
        Cookie cookie = new Cookie(cookieName, cookieValue);
        // cookie 对服务器上虚拟路径下的所有目录可见
        // 设置0为删除Cookie
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
*/

        String removeCookie = request.getParameter("removeCookie");
        CookieConfig cookieConfig = new CookieConfig();
        cookieConfig.setCookieName(removeCookie);
        cookieConfig.setPath(request.getContextPath());
//        cookieConfig.setCookieValue("Java");
        CookieUtil.removeCookie(cookieConfig, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
