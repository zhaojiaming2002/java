package net.microsoft.java.web.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.net.*;

/**
 * @description:我的第一个Servlet
 * @Date on 2022/5/9
 * @author: suche
 **/

public class HelloWorldServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("1.创建了Servlet对象");
        String contextConfigLocation = servletConfig.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation" + "的值是" + contextConfigLocation);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("2.基于xml我的第一个Servlet");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("3.销毁Servlet对象");
    }
}
