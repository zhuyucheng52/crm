package com.echo.crm.mapper;

import com.echo.crm.entry.User;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Mapper
public interface UserMapper {
    User findById(@Param("id") Long id);

    PageList<User> findUsers(@Param("key") String key, @Param("pageBounds") PageBounds pageBounds);

    void add(User user);

    User findByAccount(@Param("account") String account);

    int update(User u);
}
