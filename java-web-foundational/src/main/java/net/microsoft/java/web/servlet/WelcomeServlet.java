package net.microsoft.java.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @description:使用注解方式配置servlet
 * @Date on 2022/5/10
 * @author: suche
 **/
@WebServlet(urlPatterns = "/welcome", loadOnStartup = 1)
public class WelcomeServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("基于注解我的第一个Servlet");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
