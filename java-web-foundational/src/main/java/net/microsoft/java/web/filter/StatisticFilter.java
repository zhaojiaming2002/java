package net.microsoft.java.web.filter; /**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter("/*")
public class StatisticFilter implements Filter {
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig config) throws ServletException {
        servletContext = config.getServletContext();
        Integer count = 0;
        servletContext.setAttribute("count", count);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Integer count = (Integer) servletContext.getAttribute("count");
        count++;
        servletContext.setAttribute("count", count);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
