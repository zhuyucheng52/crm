package com.echo.crm.mapper;

import com.echo.crm.entry.User;
import com.echo.crm.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

public interface UserMapper extends BaseMapper<User> {
    User selectByAccount(@Param("account") String account);
}
