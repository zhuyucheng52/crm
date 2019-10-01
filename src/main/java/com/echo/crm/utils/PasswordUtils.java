package com.echo.crm.utils;

import com.echo.crm.properties.PasswordProperties;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.io.UnsupportedEncodingException;

/**
 * @author yucheng
 * @description
 * @create 2019-09-27 17:08
 */

public class PasswordUtils {
    public static String encoding(PasswordProperties properties, String password, String salt) throws UnsupportedEncodingException {
        String algorithmName = properties.getName();
        Integer algorithmTimes = properties.getTimes();
        SimpleHash simpleHash = new SimpleHash(algorithmName, password.getBytes("UTF-8"), salt, algorithmTimes);
        return simpleHash.toString();
    }
}
