package com.echo.crm.service;

import com.echo.crm.entry.Product;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

public interface ProductService {
    Product getById(Long id);
    Product add(Product product);
    Product update(Product product);
    PageList<Product> getProducts(String key, PageBounds pageBounds);
}
