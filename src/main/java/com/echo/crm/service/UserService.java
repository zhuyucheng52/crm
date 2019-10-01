package com.echo.crm.service;

import com.echo.crm.entry.User;

import java.io.UnsupportedEncodingException;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface UserService extends BaseService<User> {
    void updatePassword(Long userId, String newPassword, String oldPassword) throws UnsupportedEncodingException;
}
