package net.microsoft.java.web.filter; /**
 * @description: 非法字符替换
 * @Date on 2022/6/20
 * @author: suche
 **/

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/comments")
public class IllegalCharacterFilter implements Filter {
    private List<String> illegalCharacterList;

    @Override
    public void init(FilterConfig config) throws ServletException {

        try (InputStream inputStream = IllegalCharacterFilter.class.getClassLoader().getResourceAsStream("illegalCharacter.txt");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            if (null != bufferedReader) {
                String readLine = null;
                illegalCharacterList = new ArrayList<>();
                while ((readLine = bufferedReader.readLine()) != null) {
                    illegalCharacterList.add(readLine);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 要被代理的ClassLoader，要被代理类的接口 ,InvocationHandler 要增强的方法， Proxy自动创建代理类对象
     *
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest proxyHttpServletResult = (HttpServletRequest) Proxy.newProxyInstance(ServletRequest.class.getClassLoader(), new Class[]{HttpServletRequest.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String proxyMethodName = "getParameter";
                String resultValue = null;
                // 增强getParameter方法，其他方法不别动
                if (method.getName().equals(proxyMethodName)) {
                    // 调用方法
                    resultValue = (String) method.invoke(request, args);
                    if (null != resultValue && resultValue != "") {
                        if (null != illegalCharacterList && illegalCharacterList.size() > 0) {
                            for (String illegalCharacter : illegalCharacterList) {
                                if (illegalCharacter.contains(resultValue)) {
                                    String asterisk = "";
                                    for (int i = 0; i < resultValue.length(); i++) {
                                        asterisk += "*";
                                    }
                                    // 将非法字符替换为*
                                    resultValue = resultValue.replace(illegalCharacter, asterisk);
                                    System.out.println(resultValue);
                                }
                            }
                        }
                    }
                    return resultValue;
                }
                return method.invoke(request, args);

            }

        });

        // 这里一定要使用代理类的HttpServletRequest
        chain.doFilter(proxyHttpServletResult, response);
    }

    @Override
    public void destroy() {
    }

}
