package com.echo.crm.mapper;

import com.echo.crm.entry.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> selectByAccount(@Param("account") String account);
}
