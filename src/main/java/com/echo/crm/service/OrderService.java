package com.echo.crm.service;

import com.echo.crm.entry.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface OrderService {
    Order getOrderById(Long id);

    PageList<Order> getOrders(String key, PageBounds pageBounds);

    Order add(Order order);

    Order update(Order order);
}
