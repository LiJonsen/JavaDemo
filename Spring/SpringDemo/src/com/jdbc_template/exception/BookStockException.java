package com.jdbc_template.exception;

/**
 * @ClassName BookStockException
 * @Description 自定义异常-书籍库存异常
 * @Author Josen
 * @Create 2020/7/28 14:19
 */
public class BookStockException extends RuntimeException{
    public BookStockException() {
        super();
    }

    public BookStockException(String message) {
        super(message);
    }

    public BookStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookStockException(Throwable cause) {
        super(cause);
    }

    protected BookStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
