package net.microsoft.java.web.servlet;
/**
 * @description:ServletContext方法的使用
 * @Date on 2022/5/12
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(value = "/context", loadOnStartup = 1)
public class ServletContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
//        getMimeType(servletContext);

        getResourceAsStream(servletContext);
    }

    /**
     * 获取MIME类型
     *
     * @param servletContext
     */
    private void getMimeType(ServletContext servletContext) {
        String mime = "index.html";
        String mimeType = servletContext.getMimeType(mime);

        System.out.println(mime + "对应的MIME 类型" + mimeType);

        mime = "十年.flac";
        mimeType = servletContext.getMimeType(mime);
        System.out.println(mime + "对应的MIME 类型" + mimeType);
        mime = "肖生客的救赎.mp4";
        mimeType = servletContext.getMimeType(mime);
        System.out.println(mime + "对应的MIME 类型" + mimeType);
    }

    /**
     * 获取给定虚拟路径对应的真实路径。
     * @param servletContext
     */
    private void getRealPath(ServletContext servletContext) {
        String path = "static/js_regex.html";
        String realPath = servletContext.getRealPath(path);
        System.out.println(realPath);

    }

    /**
     * 将位于命名路径的资源作为InputStream对象返回
     * @param servletContext
     */
    private void getResourceAsStream(ServletContext servletContext) {
        String path = "index.html";
        InputStream resourceAsStream = servletContext.getResourceAsStream(path);
        System.out.println(resourceAsStream);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
