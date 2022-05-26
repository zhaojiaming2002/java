package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/5/23
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/request")
public class PostGetRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getRequestBodyData(request);
    }

    /**
     * HttpServletRequest获取请求行数据
     *
     * @param request
     */
    private void getRequestLineData(HttpServletRequest request) {

        System.out.println("HttpServletRequest获取请求方法---->" + request.getMethod());

        // URI 统一资源标识符径是由 项目名+资源名组成 /javaweb/request
        System.out.println("HttpServletRequest获取请求的URI---->" + request.getRequestURI());
        // URL 统一资源定位符 由 服务器+项目名+资源名 http://localhost:8080/javaweb/request
        System.out.println("HttpServletRequest获取请求的URL---->" + request.getRequestURL());
        //获取请求的项目名(虚拟路径)/javaweb
        System.out.println("HttpServletRequest获取请求的项目名(虚拟路径)---->" + request.getContextPath());

        System.out.println("HttpServletRequest获取协议信息---->" + request.getProtocol());


        System.out.println("HttpServletRequest获取请求远程地址---->" + request.getRemoteAddr());
        System.out.println("HttpServletRequest获取请求服务器端口---->" + request.getServerPort());
        System.out.println("HttpServletRequest获取GET请求(url?后的参数)---->" + request.getQueryString());
    }

    /**
     * HttpServletRequest获取请求头数据
     *
     * @param request
     */
    private void getRequestHeaderData(HttpServletRequest request) {
        System.out.println("HttpServletRequest获取请求头User-Agent数据----->" + request.getHeader("User-Agent"));
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            System.out.println("HttpServletRequest获取请求头---获取请求头的名称------>" + headerNames.nextElement());
        }
    }

    /**
     * HttpServletRequest获取请求体数据
     *
     * @param request
     */
    private void getRequestBodyData(HttpServletRequest request) {
        // Get 是没有请求体，只能获取请求参数
        // 只能获取一个请求参数
        System.out.println("HttpServletRequest获取请求体数据参数为name的值------->" + request.getParameter("name"));
        String[] hobbies = request.getParameterValues("hobby");
        // 获取参数值为一个及一个以上
        System.out.println("HttpServletRequest获取请求体数据参数为hobby的值------->" + Arrays.toString(hobbies));

        // 获取全部参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();

        Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> parameter = iterator.next();
            System.out.println(parameter.getKey() + "--->" + Arrays.toString(parameter.getValue()));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
