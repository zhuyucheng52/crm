package com.echo.crm.mapper;

import com.echo.crm.entry.Product;
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
public interface ProductMapper extends BaseMapper<Product> {
    List<Product> findOtherByName(@Param("id") Long id, @Param("name") String name);
    List<Product> findByName(String name);
}
