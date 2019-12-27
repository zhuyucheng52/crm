package com.echo.crm.service;

import com.echo.crm.entry.Permission;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @author yucheng
 * @description
 * @create 2019-12-26 3:55 下午
 */

public interface PermissionService {
	Permission findById(Long id);
	void add(Permission t);
	void update(Permission t);
	int delete(Long id);
	PageList<Permission> findByKeyword(String key, PageBounds pageBounds);
}
