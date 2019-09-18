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
        Assert.notNull(id, "用户ID不能为空");
        return userMapper.findById(id);
    }

    @Override
    public PageList<User> getUsers(String key, PageBounds pageBounds) {
        return userMapper.findUsers(key, pageBounds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User add(User user) {
        String account = user.getAccount();
        Assert.isTrue(StringUtils.isNotBlank(account), "账户不能为空");
        User u = userMapper.getByAccount(account);
        Assert.isNull(u, "账户已存在");

        userMapper.add(user);
        return getUserById(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {
        Long id = user.getId();
        Assert.notNull(id, "用户ID不能为空");

        User u = getUserById(id);
        if (null != user.getName()) {
            u.setName(user.getName());
        }
        if (null != user.getSex()) {
            u.setSex(user.getSex());
        }
        if (null != user.getBirthday()) {
            u.setBirthday(user.getBirthday());
        }
        if (null != user.getMobile()) {
            u.setMobile(user.getMobile());
        }
        if (null != user.getAddress()) {
            u.setAddress(user.getAddress());
        }
        if (null != user.getEmail()) {
            u.setEmail(user.getEmail());
        }
        if (null != user.getRemark()) {
            u.setRemark(user.getRemark());
        }

        userMapper.update(u);
        return getUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        Assert.notNull(oldPassword, "原始密码不能为空");
        Assert.notNull(newPassword, "新密码不能为空");
        User u = getUserById(id);
        if (oldPassword.equals(u.getPassword())) {
            u.setPassword(newPassword);
        } else {
            throw new IllegalArgumentException("原始密码错误");
        }

        userMapper.update(u);
    }
}
