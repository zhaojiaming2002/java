package net.microsoft.java.web.filter; /**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/request/set")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("初始化成功 LoginFilter");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println("过滤器拦截的地址是--->" + httpServletRequest.getRequestURI());

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        System.out.println("销毁成功 LoginFilter");
    }

}
