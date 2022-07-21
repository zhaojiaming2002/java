package net.microsoft.java.web.servlet; /**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/
import com.fasterxml.jackson.databind.ObjectMapper;
import net.microsoft.java.web.bean.response.ResponseBean;
import net.microsoft.java.web.service.UserService;
import net.microsoft.java.web.service.impl.UserServiceImpl;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends BaseServlet {
    /**
     * 调用UserService
     */
    UserService userService = new UserServiceImpl();

    public void checkNameValidation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        ResponseBean<Boolean> responseBean = new ResponseBean<>(Boolean.TRUE);
        String errorMessage = validation(name);
        if (errorMessage == null) {
            try {
                boolean isExists = userService.checkName(name);
                if (isExists) {
                    responseBean.setData(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorMessage = e.getMessage();
                responseBean.setErrorMessage(errorMessage);
            }
        }

        response.setContentType("text/html;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        String responseBeanJSON = objectMapper.writeValueAsString(responseBean);


        response.getWriter().write(responseBeanJSON);

    }


    public String validation(String name) {
        String errorMessage = null;
        if (name == null || name == "") {
            errorMessage = "用户名不能为空";
        }
        return errorMessage;
    }
}
