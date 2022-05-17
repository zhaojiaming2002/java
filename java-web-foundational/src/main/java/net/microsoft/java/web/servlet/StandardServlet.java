package net.microsoft.java.web.servlet; /**
 * @description:标准Serlvet创建
 * @Date on 2022/5/10
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/stand", loadOnStartup = 1)
public class StandardServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("创建Servlet成功");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("通过继承HttpServlet来构造Servlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
