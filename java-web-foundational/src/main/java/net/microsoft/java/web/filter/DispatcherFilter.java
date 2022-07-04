package net.microsoft.java.web.filter; /**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/request/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class DispatcherFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("DispatcherFilter 初始化完成");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println("拦截到" + httpServletRequest.getRequestURI());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
