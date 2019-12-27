package com.echo.crm.mapper;

import com.echo.crm.entry.Permission;
import com.echo.crm.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yucheng
 * @description
 * @create 2019-12-26 4:01 下午
 */

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
	int deleteRolePermissionRelation(@Param("permissionId") Long id);
}
