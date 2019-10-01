package com.echo.crm.exception;

/**
 * @author yucheng
 * @description
 * @create 2019-10-01 08:33
 */

public class NotSupportException extends RuntimeException {
    public NotSupportException(String msg) {
        super(msg);
    }
}
