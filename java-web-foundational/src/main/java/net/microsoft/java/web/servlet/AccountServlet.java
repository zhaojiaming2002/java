package net.microsoft.java.web.servlet;
/**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

import net.microsoft.java.web.bean.entity.Account;
import net.microsoft.java.web.bean.vo.AccountVO;
import net.microsoft.java.web.service.AccountService;
import net.microsoft.java.web.service.impl.AccountServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    /**
     * 依赖AccountService
     */
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage = null;
        response.setContentType("text/html;charset=utf-8");
        String method = request.getParameter("method");
        if ("findAllUser".equals(method)) {
            findAllUser(request, response);
        } else if ("addAccount".equals(method)) {
            String name = request.getParameter("name");
            String balance = request.getParameter("balance");
            errorMessage = validation(name, balance);
            if (errorMessage == null) {
                try {
                    Account account = new Account(name, new BigDecimal(balance));
                    boolean insert = accountService.insert(account);
                    if (insert) {
                        response.sendRedirect("account?method=findAllUser");
                    }
                } catch (Exception e) {
                    errorMessage = e.getMessage();
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher("pages/account/account_add.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("pages/account/account_add.jsp").forward(request, response);
            }
        } else if ("deleteAccountById".equals(method)) {
            deleteAccountById(request, response);
        }
    }

    public void deleteAccountById(HttpServletRequest request, HttpServletResponse response) {
        String errorMessage = null;
        String parameterId = request.getParameter("id");
        if (parameterId != null) {
            try {
                Long id = Long.valueOf(parameterId);
                boolean deleteAccountResult = accountService.deleteAccountById(id);
                if (deleteAccountResult) {
                    response.sendRedirect("account?method=findAllUser");
                }

            } catch (Exception e) {
                e.printStackTrace();
                errorMessage = e.getMessage();

                try {
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher("account?method=findAllUser").forward(request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    /**
     * 查询所有用户
     *
     * @param request
     * @param response
     */
    private void findAllUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<AccountVO> accountList = accountService.selectAll();
            if (null != accountList) {
                request.setAttribute("accountList", accountList);
                request.getRequestDispatcher("pages/account/account_list.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("index.html").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 验证用户输入
     */

    public String validation(String name, String balance) {
        String errorMessage = null;
        String regex = "[0-9]+";
        if (!balance.matches(regex)) {
            return "余额必须为数字";
        }
        if (null != name && name != "") {
            if (null != balance && balance != "" && new BigDecimal(balance).compareTo(BigDecimal.ZERO) != -1) {
                return errorMessage;
            } else {
                errorMessage = "余额不能为空或者余额小于0";
            }
        } else {
            errorMessage = "用户名不能为空";
        }
        return errorMessage;

    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
