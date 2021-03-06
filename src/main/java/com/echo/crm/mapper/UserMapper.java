package com.echo.crm.mapper;

import com.echo.crm.entry.Role;
import com.echo.crm.entry.User;
import com.echo.crm.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Repository
public interface UserMapper extends BaseMapper<User> {
    Set<String> selectRolesByUsername(@Param("username") String username);

    Set<String> selectPermissionsByUsername(@Param("username") String username);

    User selectByUsername(@Param("username") String username);

    int deleteUserRoleByUserId(@Param("userId") Long userId);

    int insertUserRoles(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    User selectById(@Param("userId") Long id);

}
