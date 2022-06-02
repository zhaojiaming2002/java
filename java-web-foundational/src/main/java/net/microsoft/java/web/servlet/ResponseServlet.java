package net.microsoft.java.web.servlet; /**
 * @description: HttpServletResponse 响应处理
 * @Date on 2022/5/29
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/response")
public class ResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        setResponseLineData(response);

//        setResponseHeaderData(response);
//        responseSendRedirect(response);
//        responsesShowInText(response);
        responseShowImage(response);
    }

    /**
     * 设置响应行数据
     *
     * @param response
     */
    private void setResponseLineData(HttpServletResponse response) {
        response.setStatus(403);
    }

    /**
     * 设置响应头数据
     *
     * @param response
     */
    private void setResponseHeaderData(HttpServletResponse response) {
        response.setHeader("refresh", "5;url=https://cn.bing.com");
    }

    /**
     * 重定向跳转
     *
     * @param response
     */
    private void responseSendRedirect(HttpServletResponse response) throws IOException {
//        // 设置重定向状态码
//        response.setStatus(302);
//        // 设置头，跳转指定页面
//        response.setHeader("Location", "https://cn.bing.com");


        response.sendRedirect("http://localhost:8080/javaweb/request?name=admin");

    }


    /**
     * 页面上展示数据
     *
     * @param response
     */
    private void responsesShowInText(HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("跟光磊学Java Web");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 页面上输出图片
     *
     * @param response
     */
    private void responseShowImage(HttpServletResponse response) {
        String file = "images/darkLight.jpg";
        String mimeType = getServletContext().getMimeType(file);
        response.setContentType(mimeType);
        try (
                InputStream resourceAsStream = this.getServletContext().getResourceAsStream(file);
                ServletOutputStream outputStream = response.getOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            int len = 0;

            while ((len = resourceAsStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }

        } catch (Exception e) {

        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
