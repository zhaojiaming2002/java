package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/5/12
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/*", loadOnStartup = 1)
public class ServletContextStatisticsServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = this.getServletContext();
        Integer count = 0;
        servletContext.setAttribute("count", count);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        count++;
        servletContext.setAttribute("count", count);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
