package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/5/12
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/info")
public class ServletContextInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("<h1>网站访问浏览:" + count + "<h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
