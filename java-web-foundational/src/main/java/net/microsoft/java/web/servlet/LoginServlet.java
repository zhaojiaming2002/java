package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/6/3
 * @author: suche
 **/

import net.microsoft.java.web.bean.entity.User;
import net.microsoft.java.web.service.UserService;
import net.microsoft.java.web.service.impl.UserServiceImpl;
import net.microsoft.java.web.util.CookieConfig;
import net.microsoft.java.web.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
     * 依赖UserService
     */
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("调用了LoginServlet doGet方法");
        // 处理POST请求乱码
        request.setCharacterEncoding("UTF-8");
        // 处理POST请求响应乱码
        response.setContentType("text/html;charset=utf-8");


        String captcha = (String) request.getSession().getAttribute("captcha");

        String clientCaptcha = request.getParameter("captcha");
        String errorMessage = null;
        if (captcha.equalsIgnoreCase(clientCaptcha)) {

            String name = request.getParameter("name");
            String password = request.getParameter("password");

            User user = new User();
            user.setName(name);
            user.setPassword(password);
            boolean login = false;
            try {
                login = userService.login(user);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (login) {
                String renumber = request.getParameter("renumber");
                if (renumber != null) {
                    CookieConfig nameConfig = new CookieConfig();
                    nameConfig.setPath(request.getContextPath());
                    nameConfig.setCookieName("name");
                    nameConfig.setCookieValue(user.getName());
                    nameConfig.setMaxAge(15 * 24 * 60 * 60);

                    CookieConfig passwordConfig = new CookieConfig();
                    passwordConfig.setPath(request.getContextPath());
                    passwordConfig.setCookieName("password");
                    passwordConfig.setCookieValue(user.getPassword());
                    passwordConfig.setMaxAge(15 * 24 * 60 * 60);
                    CookieUtil.addCookie(nameConfig, response);
                    CookieUtil.addCookie(passwordConfig, response);


                } else {
                    CookieConfig nameConfig = new CookieConfig();
                    nameConfig.setPath(request.getContextPath());
                    nameConfig.setCookieName("name");

                    CookieConfig passwordConfig = new CookieConfig();
                    passwordConfig.setPath(request.getContextPath());
                    passwordConfig.setCookieName("password");

                    CookieUtil.removeCookie(nameConfig, response);
                    CookieUtil.removeCookie(passwordConfig, response);
                    request.getSession().removeAttribute("user");
                }
                // 登录成功
                request.getSession().setAttribute("user", user);
                response.sendRedirect("pages/success.jsp");

            } else {
                //账号或者密码错误
                if (name.equals("") || password.equals("")) {
                    errorMessage = "账号或者密码不能为空";
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher("pages/login.jsp").forward(request, response);

                } else {
                    errorMessage = "账号密码错误";
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher("pages/login.jsp").forward(request, response);
                }
            }
        } else {
            errorMessage = "验证码错误";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

