package com.echo.crm.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yucheng
 * @description
 * @create 2019-12-13 13:52
 */

@Component
public class TokenCache {
    private static Cache<String, String> data = CacheBuilder.newBuilder()
            .maximumSize(1000 * 10)
            .expireAfterAccess(2, TimeUnit.HOURS)
            .expireAfterWrite(2, TimeUnit.HOURS)
            .build();

    public String getToken(String username) {
        try {
            return data.getIfPresent(username);
        } catch (Exception e) {
            return null;
        }
    }

    public void putToken(String username, String token) {
        data.put(username, token);
    }

    public void invalidateToken(String username) {
        data.invalidate(username);
    }

    public void clearTokens() {
        data.cleanUp();
    }

}
