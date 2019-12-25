package com.echo.crm.service;

import com.echo.crm.dto.LoginDTO;
import com.echo.crm.dto.TokenHandler;
import com.echo.crm.entry.User;
import com.echo.crm.exception.SystemException;
import com.echo.crm.mapper.RoleMapper;
import com.echo.crm.mapper.UserMapper;
import com.echo.crm.security.JWTProperties;
import com.echo.crm.security.JWTUtil;
import com.echo.crm.utils.PasswordUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Set;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JWTProperties jwtProperties;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        Assert.notNull(id, "用户ID不能为空");
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<User> findByKeyword(String key, PageBounds pageBounds) {
        return userMapper.selectByKeyword(key, pageBounds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User add(User user) {
        String account = user.getUsername();
        Assert.isTrue(StringUtils.isNotBlank(account), "账户不能为空");
        User u = userMapper.selectByUsername(account);
        Assert.isNull(u, String.format("账户[%s]已存在", account));
        String password = user.getPassword();
        if (password != null) {
            user.setPassword(PasswordUtil.encodedPassword(password));
        }

        userMapper.insertSelective(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {
        // 该接口不支持密码更新
        Assert.isNull(user.getPassword(), String.format("用户[%s]密码更新失败", user.getId()));
        Long id = user.getId();
        String account = user.getUsername();

        User u = findById(id);
        Assert.notNull(u, String.format("用户[%s]不存在", id));

        userMapper.updateByPrimaryKeySelective(user);
        return findById(id);
    }

    /**
     * 更新用户角色
     * @param user
     */
    private void updateUserRole(User user) {
        userMapper.deleteUserRoleByUserId(user.getId());
        userMapper.insertUseeRoles(user.getId(), user.getRoles());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        User u = findById(id);
        Assert.notNull(u, "客户不存在");
        u.setDisabled(true);
        userMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long userId, String newPassword, String oldPassword) {
        User u = findById(userId);
        Assert.notNull(u, String.format("用户[%s]不存在", userId));

        String encodedPassword = PasswordUtil.encodedPassword(oldPassword);
        Assert.isTrue(StringUtils.equals(u.getPassword(), encodedPassword), "密码不一致");
        u.setPassword(newPassword);
        userMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    public TokenHandler login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = PasswordUtil.encodedPassword(loginDTO.getPassword());

        User u = findByUsername(username);
        if (u == null) {
            log.warn("User: {} is not exists", username);
            throw new SystemException("用户不存在");
        }
        if (StringUtils.equals(u.getPassword(), password)) {
            String secret = StringUtils.right(username, 5) + JWTProperties.SIGN_KEY;
            String token = JWTUtil.sign(username, secret, JWTProperties.TOKEN_EXPIRE_TIME);
            ThreadContext.put("token", token);
            return new TokenHandler(token);
        } else {
            log.warn("User: {} login failure", username);
            throw new SystemException("用户名或密码不正确");
        }
    }

    @Override
    public User findByToken(String token) {
        String username = JWTUtil.getUsername(token);
        return findByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public Set<String> findRolesByUsername(String username) {
        return userMapper.selectRolesByUsername(username);
    }

    @Override
    public Set<String> findPermissionsByUsername(String username) {
        return userMapper.selectPermissionsByUsername(username);
    }

    private Long getCurrentUser() {
        // TODO
        return null;
    }
}
