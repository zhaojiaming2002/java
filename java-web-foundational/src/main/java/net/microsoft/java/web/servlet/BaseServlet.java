package net.microsoft.java.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求编码
        request.setCharacterEncoding("utf-8");
        // 设置响应编码
        response.setCharacterEncoding("utf-8");
        // 设置响应格式
        String method = request.getParameter("method");
        try {
            Method[] publicMethod = this.getClass().getMethods();
            if (publicMethod.length > 0 && publicMethod != null) {
                for (Method cntMethod : publicMethod) {
                    if (cntMethod.getName().equals(method)) {
                        cntMethod.invoke(this, request, response);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
