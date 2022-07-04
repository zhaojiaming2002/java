package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/6/13
 * @author: suche
 **/


import net.microsoft.java.web.bean.vo.UserVO;
import net.microsoft.java.web.bean.entity.User;
import net.microsoft.java.web.service.UserService;
import net.microsoft.java.web.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/list")
public class UserListServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = null;
        try {
            userList = userService.findAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<UserVO> userVOList = null;
        if (userList != null && userList.size() > 0) {
            userVOList = new ArrayList<>();
            for (User user : userList) {
                UserVO userVO = new UserVO();
                userVO.setId(user.getId());
                userVO.setName(user.getName());
                userVO.setPassword(user.getPassword());
                userVO.setCreateDate(user.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
                userVO.setUpDateDate(user.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
                userVOList.add(userVO);

            }
        }

        request.setAttribute("userVOList", userVOList);
        request.getRequestDispatcher("/pages/user_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
