package com.echo.crm.mapper;

import com.echo.crm.entry.User;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Mapper
public interface UserMapper {
    User findById(Long id);

    PageList<User> findUsers(PageBounds pageBounds);

    void add(User user);

    User getByAccount(@Param("account") String account);

    int update(User u);
}
