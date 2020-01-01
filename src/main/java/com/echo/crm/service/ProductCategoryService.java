package com.echo.crm.service;

import com.echo.crm.entry.ProductCategory;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface ProductCategoryService {
	ProductCategory findById(Long id);
	void add(ProductCategory t);
	int update(ProductCategory t);
	void delete(Long id);
	PageList<ProductCategory> findByKeyword(String key, PageBounds pageBounds);
}
