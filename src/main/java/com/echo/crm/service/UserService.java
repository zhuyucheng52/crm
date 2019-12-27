package com.echo.crm.service;

import com.echo.crm.dto.LoginDTO;
import com.echo.crm.dto.TokenHandler;
import com.echo.crm.entry.User;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import java.util.Set;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface UserService {
	User findById(Long id);

	void add(User t);

	void update(User t);

	void delete(Long id);

	PageList<User> findByKeyword(String key, PageBounds pageBounds);

	void updatePassword(Long userId, String newPassword, String oldPassword);

	TokenHandler login(LoginDTO loginDTO);

	User findByToken(String token);

	User findByUsername(String username);

	Set<String> findRolesByUsername(String username);

	Set<String> findPermissionsByUsername(String username);
}
