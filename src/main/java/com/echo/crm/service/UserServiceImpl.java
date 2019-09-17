package com.echo.crm.service;

import com.echo.crm.dto.ResultDTO;
import com.echo.crm.entry.User;
import com.echo.crm.mapper.UserMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> getUsers(PageBounds pageBounds) {
        return userMapper.findUsers(pageBounds);
    }
}
