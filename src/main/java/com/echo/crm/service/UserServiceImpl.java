package com.echo.crm.service;

import com.echo.crm.dto.LoginDTO;
import com.echo.crm.dto.TokenHandler;
import com.echo.crm.entry.Role;
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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        Assert.notNull(id, "用户ID不能为空");
        return userMapper.selectById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<User> findByKeyword(String key, PageBounds pageBounds) {
        PageList<User> users = userMapper.selectByKeyword(key, pageBounds);
        final Map<Long, User> idUserMap = new HashMap<>();
        users.forEach(u -> idUserMap.put(u.getId(), u));

        // 填充角色信息
        List<Role> roles = roleMapper.selectByUserIds(idUserMap.keySet());
        roles.forEach(r -> idUserMap.get(r.getUserId()).getRoles().add(r));

        return users;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(User user) {
        String account = user.getUsername();
        Assert.isTrue(StringUtils.isNotBlank(account), "账户不能为空");
        User u = userMapper.selectByUsername(account);
        Assert.isNull(u, String.format("账户[%s]已存在", account));
        String password = user.getPassword();
        if (password != null) {
            user.setPassword(PasswordUtil.encodedPassword(password));
        }
        userMapper.insertSelective(user);

        List<Role> roles = user.getRoles();
        if (CollectionUtils.isNotEmpty(roles)) {
            List<Long> roleIds = roles.stream().map(r -> r.getId()).collect(Collectors.toList());
            userMapper.insertUserRoles(user.getId(), roleIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User user) {
        Assert.notNull(user.getId(), "用户ID不能为空");
        // 不支持用户名和密码更新
    	user.setUsername(null);
		user.setPassword(null);
        updateUserRole(user);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 更新用户角色
     * @param user
     */
    private void updateUserRole(User user) {
        List<Role> roles = user.getRoles();
        if (CollectionUtils.isNotEmpty(roles)) {
            userMapper.deleteUserRoleByUserId(user.getId());
            List<Long> roleIds = roles.stream().map(r -> r.getId()).collect(Collectors.toList());
            userMapper.insertUserRoles(user.getId(), roleIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
    	User u = new User();
    	u.setId(id);
    	u.setDeleted(true);
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
