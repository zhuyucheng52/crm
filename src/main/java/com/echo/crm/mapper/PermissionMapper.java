package com.echo.crm.mapper;

import com.echo.crm.entry.Permission;
import com.echo.crm.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author yucheng
 * @description
 * @create 2019-12-26 4:01 下午
 */

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
	int deleteRolePermissionRelation(@Param("permissionId") Long id);
	List<Permission> selectByRoleIds(@Param("roleIds") Set<Long> keySet);
}
