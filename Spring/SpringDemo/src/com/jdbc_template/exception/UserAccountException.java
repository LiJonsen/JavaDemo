package com.jdbc_template.exception;

/**
 * @ClassName UserAccountException
 * @Description 自定义异常-用户账户异常
 * @Author Josen
 * @Create 2020/7/28 14:19
 */
public class UserAccountException extends RuntimeException{
    public UserAccountException() {
        super();
    }
    public UserAccountException(String message) {
        super(message);
    }

    public UserAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAccountException(Throwable cause) {
        super(cause);
    }

    protected UserAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
