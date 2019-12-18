package com.echo.crm.service;

import com.echo.crm.dto.TokenHandler;
import com.echo.crm.entry.User;

import java.util.Set;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface UserService extends BaseService<User> {
    void updatePassword(Long userId, String newPassword, String oldPassword);

    TokenHandler login(User user);

    User findByToken(String token);

    User findByUsername(String username);

    Set<String> findRolesByUsername(String username);

    Set<String> findPermissionsByUsername(String username);
}
