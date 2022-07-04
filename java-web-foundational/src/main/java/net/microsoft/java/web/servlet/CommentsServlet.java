package net.microsoft.java.web.servlet; /**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/comments")
public class CommentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comments = request.getParameter("comments");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(comments);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
