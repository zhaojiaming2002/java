package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/6/3
 * @author: suche
 **/

import net.microsoft.java.web.entity.User;
import net.microsoft.java.web.service.UserService;
import net.microsoft.java.web.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
     * 依赖UserService
     */
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求编码
        request.setCharacterEncoding("utf-8");
        // 设置响应编码
        response.setCharacterEncoding("utf-8");

        User user = new User();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(user.getName());
        System.out.println(user.getPassword());

        boolean login = userService.login(user);
        response.setContentType("text/html; charset=UTF-8");
        if (login) {
            response.getWriter().write("<h1>登录成功</h1>");
        } else {
            response.getWriter().write("<h1>登录失败</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

