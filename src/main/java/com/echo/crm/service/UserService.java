package com.echo.crm.service;

import com.echo.crm.entry.User;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface UserService {
    User getUserById(Long id);

    List<User> getUsers(PageBounds pageBounds);

    User add(User user);

    User update(User user);
}
