package com.echo.crm.service;

import com.echo.crm.entry.Permission;
import com.echo.crm.entry.Role;
import com.echo.crm.mapper.PermissionMapper;
import com.echo.crm.mapper.RoleMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public Role findById(Long id) {
		Assert.notNull(id, "角色ID不能为空");
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageList<Role> findByKeyword(String key, PageBounds pageBounds) {
		PageList<Role> roles = roleMapper.selectByKeyword(key, pageBounds);
		final Map<Long, Role> idRoleMap = new HashMap<>();
		roles.forEach(r -> idRoleMap.put(r.getId(), r));

		List<Permission> permissions = permissionMapper.selectByRoleIds(idRoleMap.keySet());
		permissions.forEach(p -> idRoleMap.get(p.getRoleId()).getPermissions().add(p));
		return roles;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void add(Role role) {
		roleMapper.insert(role);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(Role role) {
		return roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		roleMapper.deleteByPrimaryKey(id);
	}
}
