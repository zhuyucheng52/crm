package com.echo.crm.mapper;

import com.echo.crm.entry.Role;
import com.echo.crm.utils.BaseMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Repository
public interface RoleMapper extends BaseMapper<Role> {
    PageList<Role> selectByUserId(@Param("userId") Long userId);
}
