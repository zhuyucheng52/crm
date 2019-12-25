package com.echo.crm.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.echo.crm.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
public class JWTUtil {
    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;
    public static final int EXPIRED = 2;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static int verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
            return SUCCESS;
        } catch (TokenExpiredException e) {
            log.warn("User: {} token: {} verify failure", username, token, e);
            return EXPIRED;
        } catch (Exception e) {
            log.warn("User: {} token: {} verify failure", username, token, e);
            return FAILURE;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            log.warn("Get username from token: {} failure", token, e);
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @param token token中包含过期时间
     * @return
     */
    public static long getExpired(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("exp").asLong();
        } catch (Exception e) {
            log.warn("Get expired from token: {} failure", token, e);
            return 0;
        }
    }

    /**
     * 基于已有token生成新token
     * @param token
     * @return
     */
    public static String newTokenIfNecessary(String token) {
        long expired = getExpired(token) * 1000;
        if (System.currentTimeMillis() + (JWTProperties.TOKEN_EXPIRE_TIME / 2) > expired) {
            String username = getUsername(token);
            return sign(username, JWTProperties.SIGN_KEY, JWTProperties.TOKEN_EXPIRE_TIME);
        }
        return token;
    }

    /**
     * 生成签名
     * @param username 用户名
     * @param secret 签名秘钥
     * @return 加密的token
     */
    public static String sign(String username, String secret, Long expireTime) {
        try {
            Date date = new Date(System.currentTimeMillis() + expireTime);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.warn("Sign username: {} with secret: {} failure", username, secret, e);
            return null;
        }
    }
}
