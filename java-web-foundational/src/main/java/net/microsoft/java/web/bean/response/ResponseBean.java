package net.microsoft.java.web.bean.response;

/**
 * @description: 响应给客户端的数据
 * @Date on 2022/6/20
 * @author: suche
 **/

public class ResponseBean<T> {
    /**
     * 服务器状态
     */
    private boolean flag;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 错误数据访问
     */
    private String errorMessage;

    public ResponseBean(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
