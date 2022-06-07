package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/6/5
 * @author: suche
 **/

import net.microsoft.java.web.util.CookieConfig;
import net.microsoft.java.web.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@WebServlet("/session/set")
public class HttpSessionSetServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(String.valueOf(HttpSessionSetServlet.class));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("courseName", "跟光磊学Java");

        CookieConfig cookieConfig = new CookieConfig();
        cookieConfig.setCookieName("JSESSIONID");
        cookieConfig.setCookieValue(session.getId());
        logger.info("session-->" + session.getId());
        cookieConfig.setMaxAge(30 * 60);
        cookieConfig.setPath(request.getContextPath());
        CookieUtil.addCookie(cookieConfig, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
