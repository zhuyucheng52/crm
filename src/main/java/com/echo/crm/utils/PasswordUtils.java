package com.echo.crm.utils;

import com.echo.crm.properties.PasswordProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.nio.charset.StandardCharsets;

/**
 * @author yucheng
 * @description
 * @create 2019-09-27 17:08
 */

@Slf4j
public class PasswordUtils {
    public static String encoding(PasswordProperties properties, String password, String salt) {
        String algorithmName = properties.getName();
        Integer algorithmTimes = properties.getTimes();
        SimpleHash simpleHash = null;
        simpleHash = new SimpleHash(algorithmName, password.getBytes(StandardCharsets.UTF_8), salt, algorithmTimes);
        return simpleHash.toString();
    }
}
