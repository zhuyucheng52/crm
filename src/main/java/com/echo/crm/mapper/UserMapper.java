package com.echo.crm.mapper;

import com.echo.crm.dto.ResultDTO;
import com.echo.crm.entry.User;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Mapper
public interface UserMapper {
    User findById(Long id);

    List<User> findUsers(PageBounds pageBounds);
}
