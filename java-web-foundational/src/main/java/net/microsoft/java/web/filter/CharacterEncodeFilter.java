package net.microsoft.java.web.filter; /**
 * @description:POST请求POST响应乱码处理
 * @Date on 2022/6/20
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter("/*")
public class CharacterEncodeFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * Filter 处理POST请求和响应乱码
     *
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
