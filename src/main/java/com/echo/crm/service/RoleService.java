package com.echo.crm.service;

import com.echo.crm.entry.Role;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface RoleService {
	Role findById(Long id);
	void add(Role role);
	int update(Role role);
	void delete(Long id);
	PageList<Role> findByKeyword(String key, PageBounds pageBounds);
}
