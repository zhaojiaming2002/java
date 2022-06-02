package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/6/2
 * @author: suche
 **/

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.Objects;

@WebServlet("/download")
public class DownLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // http://localhost:8080/javaweb/download?fileName=%E6%9D%8E%E7%91%9E%E5%8D%8E.jpg 文件含有中文


        String fileName = request.getParameter("fileName");

        String path = "images/" + fileName;
        String mimeType = getServletContext().getMimeType(fileName);
        response.setHeader("Content-Type", mimeType);

        // 防止中文解析不出来
        String encode = URLEncoder.encode(fileName, "UTF-8");
        // 告诉浏览器下载而不是显示
        response.setHeader("Content-Disposition", "attachment;filename=" + encode);

        String test = null;
        try (
                InputStream inputStream = getServletContext().getResourceAsStream(path);
                ServletOutputStream outputStream = response.getOutputStream();
        ) {

            IOUtils.copy(inputStream, outputStream);
//            byte[] buffer = new byte[8192];
//            int len = 0;
//            while ((len = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, len);
//
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
