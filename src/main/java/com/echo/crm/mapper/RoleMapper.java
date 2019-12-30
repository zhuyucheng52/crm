package com.echo.crm.mapper;

import com.echo.crm.entry.Permission;
import com.echo.crm.entry.Role;
import com.echo.crm.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Repository
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectByUserId(@Param("userId") Long userId);
    List<Role> selectByUserIds(@Param("userIds") Collection<Long> userIds);
	int deleteRolePermissionByRoleId(@Param("roleId") Long id);
	int insertRolePermission(@Param("roleId") Long id, @Param("permissions") List<Permission> permissions);
	Role selectById(@Param("roleId") Long id);
}
