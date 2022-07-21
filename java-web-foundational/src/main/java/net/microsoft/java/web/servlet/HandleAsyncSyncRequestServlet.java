package net.microsoft.java.web.servlet; /**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/handle")
public class HandleAsyncSyncRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        System.out.println("获取请求参数值为->" + name + "请求方法->" + request.getMethod());
        response.setContentType("text/html");
        System.out.println(1 / 0);
        response.getWriter().write("响应数据" + name + "->" + request.getMethod());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
