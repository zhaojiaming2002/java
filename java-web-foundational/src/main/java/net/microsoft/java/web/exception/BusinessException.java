package net.microsoft.java.web.exception;

/**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

public class BusinessException extends RuntimeException {
    public BusinessException() {
        super();
    }

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}
