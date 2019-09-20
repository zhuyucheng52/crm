package com.echo.crm.mapper;

import com.echo.crm.entry.Order;
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
public interface OrderMapper {
    Order findById(@Param("id") Long id);

    PageList<Order> findOrders(@Param("key") String key, @Param("pageBounds") PageBounds pageBounds);

    void add(Order user);

    int update(Order o);
}
