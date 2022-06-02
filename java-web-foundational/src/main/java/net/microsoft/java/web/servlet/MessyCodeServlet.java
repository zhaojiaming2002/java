package net.microsoft.java.web.servlet;
/**
 * @description:处理请求和响应乱码
 * @Date on 2022/5/29
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/messy/code")
public class MessyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求方式为:" + request.getMethod());
        // 处理请求乱码
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        // PrintWriter write() 只能输出字符串
        // PrintWriter println() 可以输出字符串和数字

        // 处理响应乱码
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("<h1>学习JavaWeb</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
