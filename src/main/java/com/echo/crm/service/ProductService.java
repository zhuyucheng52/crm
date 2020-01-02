package com.echo.crm.service;

import com.echo.crm.entry.Product;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface ProductService {
	Product findById(Long id);
	void add(Product t);
	int update(Product t);
	void delete(Long id);
	PageList<Product> findByKeyword(String key, PageBounds pageBounds);
	PageList<Product> findByCategoryId(Long categoryId);
}
