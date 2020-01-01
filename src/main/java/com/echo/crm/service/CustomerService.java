package com.echo.crm.service;

import com.echo.crm.entry.Customer;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface CustomerService {
	Customer findById(Long id);
	void add(Customer customer);
	int update(Customer customer);
	void delete(Long id);
	PageList<Customer> findByKeyword(String key, PageBounds pageBounds);
}
