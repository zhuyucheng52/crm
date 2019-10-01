package com.echo.crm.service;

import com.echo.crm.entry.Role;
import com.echo.crm.entry.User;
import com.echo.crm.mapper.RoleMapper;
import com.echo.crm.mapper.UserMapper;
import com.echo.crm.properties.PasswordProperties;
import com.echo.crm.utils.PasswordUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;
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

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PasswordProperties passwordProperties;

    @Override
    public User findById(Long id) {
        Assert.notNull(id, "用户ID不能为空");
        User u = userMapper.selectById(id);
        return u;
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
        return userMapper.selectById(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {
        // 该接口不支持密码更新
        Assert.isNull(user.getPassword(), String.format("用户[%s]密码更新失败", user.getId()));

        Long id = user.getId();
        String account = user.getAccount();

        User u = findById(id);
        Assert.notNull(u, String.format("用户[%s]不存在", id));

        Assert.isTrue(StringUtils.equals(account, u.getAccount()), "账号不能修改");

        userMapper.updateByPrimaryKeySelective(user);
        return userMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User delete(Long id) {
        User u = findById(id);
        Assert.notNull(u, "客户不存在");
        u.setDisabled(1);
        userMapper.updateByPrimaryKeySelective(u);

        return userMapper.selectById(id);
    }

    @Override
    public void updatePassword(Long userId, String newPassword, String oldPassword) throws UnsupportedEncodingException {
        User u = findById(userId);
        Assert.notNull(u, String.format("用户[%s]不存在", userId));

        String password = PasswordUtils.encoding(passwordProperties, oldPassword, u.getTenantId());
        Assert.isTrue(StringUtils.equals(u.getPassword(), password), "密码不一致");
        u.setPassword(newPassword);
        userMapper.updateByPrimaryKeySelective(u);
    }

    private Long getCurrentUser() {
        // TODO
        return null;
    }
}
