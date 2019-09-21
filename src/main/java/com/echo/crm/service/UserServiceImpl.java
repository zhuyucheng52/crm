package com.echo.crm.service;

import com.echo.crm.entry.User;
import com.echo.crm.mapper.UserMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
    public User findById(Long id) {
        Assert.notNull(id, "用户ID不能为空");
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageList<User> findByKeyword(String key, PageBounds pageBounds) {
        return userMapper.selectByKeyword(key, pageBounds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User add(User user) {
        String account = user.getAccount();
        Assert.isTrue(StringUtils.isNotBlank(account), "账户不能为空");
        List<User> sameAccountUsers = userMapper.selectByAccount(account);
        Assert.isTrue(sameAccountUsers.isEmpty(), "账户已存在");

        userMapper.insertSelective(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {
        Long id = user.getId();
        String account = user.getAccount();

        User u = findById(id);
        Assert.notNull(u, String.format("用户[%s]不存在", id));

        Assert.isTrue(StringUtils.equals(account, u.getAccount()), "账号不能修改");

        userMapper.updateByPrimaryKeySelective(user);
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User delete(Long id) {
        // TODO yucheng
        return null;
    }
}
