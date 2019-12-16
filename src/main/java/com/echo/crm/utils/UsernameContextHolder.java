package com.echo.crm.utils;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 18:33
 */

public class UsernameContextHolder {
    private static final ThreadLocal<Long> contextHolder = new ThreadLocal();

    public UsernameContextHolder() {
    }

    public static Long getTenantId() {
        return contextHolder.get();
    }

    public static void setTenantId(Long username) {
        contextHolder.set(username);
    }

    public static void clearTenantId() {
        contextHolder.remove();
    }
}
