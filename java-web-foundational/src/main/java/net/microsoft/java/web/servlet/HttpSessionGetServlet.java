package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/6/5
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/session/get")
public class HttpSessionGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String courseName = (String) session.getAttribute("courseName");
        System.out.println("Session--courseName的值是:" + courseName);
//        System.out.println("Session--id的值是:" + id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
