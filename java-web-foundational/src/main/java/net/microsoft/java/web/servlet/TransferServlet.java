package net.microsoft.java.web.servlet; /**
 * @description:${description}
 * @Date on 2022/6/19
 * @author: suche
 **/

import net.microsoft.java.web.service.AccountService;
import net.microsoft.java.web.service.impl.AccountServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    /**
     * 依赖Service层
     */
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String fromAccountName = request.getParameter("fromAccountName");
        String toAccountName = request.getParameter("toAccountName");
        String amount = request.getParameter("amount");
        String transferMessage = validation(fromAccountName, toAccountName, amount);

        if (transferMessage == null) {
            try {
                boolean transferResult = accountService.transferAccounts(fromAccountName, toAccountName, new BigDecimal(amount));
                if (transferResult) {
                    transferMessage = "转账成功";
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                transferMessage = e.getMessage();
            }
        }
        request.setAttribute("transferMessage", transferMessage);
        request.getRequestDispatcher("/pages/transfer.jsp").forward(request, response);

    }

    /**
     * 请求参数校验
     *
     * @param fromAccountName 转出账户名
     * @param toAccountName   转入账户名
     * @param amount          转出金额
     * @return 返回校验结果
     */
    private String validation(String fromAccountName, String toAccountName, String amount) {
        String transferMessage = null;
        if (fromAccountName == null || fromAccountName == "") {
            transferMessage = "转出账户不能为空";
        } else if (toAccountName == null || toAccountName == "") {
            transferMessage = "转入账户不能为空";
        } else if (amount == null || amount == "" || !(new BigDecimal(amount).compareTo(BigDecimal.ZERO) == 1)) {
            transferMessage = "转账金额不能为空或者金额不能小于0";
        }
        return transferMessage;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
