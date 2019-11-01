package com.echo.crm.exception;

/**
 * @author yucheng
 * @description
 * @create 2019-10-01 08:33
 */

public class SystemException extends RuntimeException {
    public SystemException(String msg) {
        super(msg);
    }

    public SystemException(Throwable e) {
        super(e);
    }
}
