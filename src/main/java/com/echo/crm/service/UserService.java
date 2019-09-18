package com.echo.crm.service;

import com.echo.crm.entry.User;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface UserService {
    User getUserById(Long id);

    PageList<User> getUsers(String key, PageBounds pageBounds);

    User add(User user);

    User update(User user);
}
