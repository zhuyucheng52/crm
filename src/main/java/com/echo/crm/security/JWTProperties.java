package com.echo.crm.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yucheng
 * @description
 * @create 2019-12-25 2:50 下午
 */

@Data
@Component
public class JWTProperties {
    @Value("#{'${jwt.anonymous-urls}'.split(',')}")
    private Set<String> anonymousUrls = new HashSet<>();

    public static long TOKEN_EXPIRE_TIME = 10 * 60 * 1000;

    /**
     * token签名秘钥
     */
    public static String SIGN_KEY = "echo";

    /**
     * token名称
     */
    public static final String TOKEN_NAME = "TOKEN";
}
