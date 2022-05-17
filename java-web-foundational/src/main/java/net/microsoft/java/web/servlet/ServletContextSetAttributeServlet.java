package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/5/12
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/set", loadOnStartup = 1)
public class ServletContextSetAttributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        servletContext.setAttribute("name", "tony");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
