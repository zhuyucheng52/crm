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
	Order findById(Long id);
	void add(Order order);
	int update(Order order);
	int delete(Long id);
	PageList<Order> findByKeyword(String key, PageBounds pageBounds);
}
