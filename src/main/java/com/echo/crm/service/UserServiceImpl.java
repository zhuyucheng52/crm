package com.echo.crm.service;

import com.echo.crm.dto.TokenHandler;
import com.echo.crm.dto.UserDTO;
import com.echo.crm.entry.Role;
import com.echo.crm.entry.User;
import com.echo.crm.exception.SystemException;
import com.echo.crm.mapper.RoleMapper;
import com.echo.crm.mapper.UserMapper;
import com.echo.crm.utils.BeanQuietUtils;
import com.echo.crm.utils.Consts;
import com.echo.crm.utils.JWTUtil;
import com.echo.crm.utils.PasswordUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        Assert.notNull(id, "用户ID不能为空");
        User u = userMapper.selectByPrimaryKey(id);
        UserDTO dto = null;
        if (u != null) {
            dto = new UserDTO();
            BeanQuietUtils.copyProperties(dto, u);
            return dto;
        } else {
            return dto;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<User> findByKeyword(String key, PageBounds pageBounds) {
        PageList<User> users = userMapper.selectByKeyword(key, pageBounds);
        final PageList<User> userDTOs = new PageList<>(users.getPaginator());
        final Map<Long, UserDTO> roleIdUserMap = new HashMap<>();
        users.stream().forEach(u -> {
            UserDTO dto = new UserDTO();
            try {
                BeanQuietUtils.copyProperties(dto, u);
                dto.setPassword(null);
                roleIdUserMap.put(u.getId(), dto);
            } catch (Exception e) {
                log.warn("Copy properties failure", u);
                throw new SystemException(e);
            }
            userDTOs.add(dto);
        });

        // 填充角色信息
        if (MapUtils.isNotEmpty(roleIdUserMap)) {
            List<Role> roles = roleMapper.selectByUserIds(roleIdUserMap.keySet());
            roles.stream().forEach(r -> roleIdUserMap.get(r.getUserId()).getRoles().add(r.getName()));
        }

        return userDTOs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User add(User user) {
        String account = user.getUsername();
        Assert.isTrue(StringUtils.isNotBlank(account), "账户不能为空");
        List<User> sameAccountUsers = userMapper.selectByAccount(account);
        Assert.isTrue(sameAccountUsers.isEmpty(), "账户已存在");

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

        Assert.isTrue(StringUtils.equals(account, u.getUsername()), "账号不能修改");

        userMapper.updateByPrimaryKeySelective(user);
        return findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User delete(Long id) {
        User u = findById(id);
        Assert.notNull(u, "客户不存在");
        u.setDisabled(true);
        userMapper.updateByPrimaryKeySelective(u);

        return findById(id);
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
    public TokenHandler login(User user) {
        String username = user.getUsername();
        String password = PasswordUtil.encodedPassword(user.getPassword());

        User u = findByUsername(username);
        if (u == null) {
            log.warn("User: {} is not exists", username);
            throw new SystemException("用户不存在");
        }
        if (StringUtils.equals(u.getPassword(), password)) {
            String token = JWTUtil.sign(username, password);
            return new TokenHandler(token);
        } else {
            log.warn("User: {} login failure", username);
            throw new SystemException("用户名或密码不正确");
        }
    }

    @Override
    public UserDTO findByToken(String token) {
        String username = JWTUtil.getUsername(token);
        User u = findByUsername(username);
        UserDTO userDTO = new UserDTO();
        BeanQuietUtils.copyProperties(userDTO, u);

        userDTO.setRoles(findRolesByUsername(username));
        userDTO.setPermissions(findPermissionsByUsername(username));
        userDTO.setPassword(null);
        return userDTO;
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
