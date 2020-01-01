package com.echo.crm.mapper;

import com.echo.crm.entry.ProductCategory;
import com.echo.crm.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 16:08
 */

@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    List<ProductCategory> selectByName(@Param("name") String name);

    List<ProductCategory> selectSameNameCategories(@Param("name") String name, @Param("id") Long id);
}
