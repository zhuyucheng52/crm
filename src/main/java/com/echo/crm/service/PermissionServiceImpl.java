package com.echo.crm.service;

import com.echo.crm.entry.Permission;
import com.echo.crm.mapper.PermissionMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yucheng
 * @description
 * @create 2019-12-26 3:56 下午
 */

@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public Permission findById(Long id) {
		// TODO yucheng
		return null;
	}

	@Override
	public void add(Permission t) {
		// TODO yucheng
	}

	@Override
	public void update(Permission t) {
		Assert.notNull(t.getId(), "权限ID不能为空");
		permissionMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int delete(Long id) {
		permissionMapper.deleteRolePermissionRelation(id);
		return permissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageList<Permission> findByKeyword(String key, PageBounds pageBounds) {
		return permissionMapper.selectByKeyword(key, pageBounds);
	}

	@Override
	public List<Permission> findPermissionTree() {
		List<Permission> permissions = permissionMapper.selectAll();
		List<Permission> permissionTree = getRoots(permissions);
		buildPermissionTree(permissionTree, permissions);
		return permissionTree;
	}

	private void buildPermissionTree(List<Permission> roots, List<Permission> permissions) {
		for (Permission p : permissions) {
			for (Permission root : roots) {
				if (p.getParentId().equals(root.getId())) {
					root.getChildren().add(p);
				}
			}
		}
	}

	private List<Permission> getRoots(List<Permission> permissions) {
		return permissions.stream().filter(p -> p.getParentId() == 0).collect(Collectors.toList());
	}
}
