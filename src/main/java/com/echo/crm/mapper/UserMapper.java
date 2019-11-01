package com.echo.crm.mapper;

import com.echo.crm.entry.User;
import com.echo.crm.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> selectByAccount(@Param("account") String account);
//    User selectById(@Param("id") Long id);
}
