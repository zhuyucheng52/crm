package com.echo.crm.mapper;

import com.echo.crm.entry.Product;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 16:08
 */

@Mapper
public interface ProductMapper {
    void add(Product product);

    Product findById(@Param("id") Long id);

    List<Product> findOtherByName(@Param("id") Long id, @Param("name") String name);

    int update(Product product);

    List<Product> findByName(String name);

    PageList<Product> findProducts(String key, PageBounds pageBounds);
}
