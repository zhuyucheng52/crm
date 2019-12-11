package com.echo.crm.service;

import com.echo.crm.dto.Token;
import com.echo.crm.dto.UserDTO;
import com.echo.crm.entry.User;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface UserService extends BaseService<User> {
    void updatePassword(Long userId, String newPassword, String oldPassword);

    Token login(User user);

    UserDTO findByToken(String token);
}
