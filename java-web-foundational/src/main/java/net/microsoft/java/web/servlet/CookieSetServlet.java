package net.microsoft.java.web.servlet; /**
 * @description:设置Cookie的值
 * @Date on 2022/6/4
 * @author: suche
 **/

import net.microsoft.java.web.util.CookieConfig;
import net.microsoft.java.web.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/cookie/set")
public class CookieSetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookieName = "courseName";
        String cookieValue = "Java";
        /*
        // 创建Cookie
        Cookie cookie = new Cookie(cookieName, cookieValue);
        // 响应给浏览器Cookie的值

        // cookie 对服务器上虚拟路径下的所有目录可见
        cookie.setPath(request.getContextPath());
        // 设置Cookie存储7天
        cookie.setMaxAge(7 * 24 * 60 * 60);

        response.addCookie(cookie);
        */

        CookieConfig cookieConfig = new CookieConfig();
        cookieConfig.setCookieName(cookieName);
        cookieConfig.setCookieValue(cookieValue);
        cookieConfig.setPath(request.getContextPath());

        CookieUtil.addCookie(cookieConfig, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
