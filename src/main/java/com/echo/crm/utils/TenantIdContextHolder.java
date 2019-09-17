package com.echo.crm.utils;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 18:33
 */

public class TenantIdContextHolder {
    private static final ThreadLocal<Long> contextHolder = new ThreadLocal();

    public TenantIdContextHolder() {
    }

    public static Long getTenantId() {
        return contextHolder.get();
    }

    public static void setTenantId(Long tenantId) {
        contextHolder.set(tenantId);
    }

    public static void clearTenantId() {
        contextHolder.remove();
    }
}
