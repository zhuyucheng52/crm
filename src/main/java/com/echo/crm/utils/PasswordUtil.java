package com.echo.crm.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;


public class PasswordUtil {

    //加密算法
    private static final String algorithmName = "SHA-256";
    private static final String salt = "echo";

    //数据库存储的私盐
    private static final int hashIterations = 2;

//    public static String enpassword(String privateSalt, String password) {
//        SimpleHash hash = new SimpleHash(algorithmName, password, salt + privateSalt, hashIterations);
//        String encodedPassword = hash.toHex();
//        return encodedPassword;
//    }

    //用于私盐和secret
//    public static String secretRandom() {
//        return new SecureRandomNumberGenerator().nextBytes().toHex();
//    }


    /**
     * 获取加密密码-获取加密密码
     */
    public static String encodedPassword(String password) {
        SimpleHash hash = new SimpleHash(algorithmName, password, salt, hashIterations);
        return hash.toHex();
    }


    public static void main(String[] args) {

    }

}
