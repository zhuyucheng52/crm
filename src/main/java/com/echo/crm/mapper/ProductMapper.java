package com.echo.crm.mapper;

import com.echo.crm.entry.Product;
import com.echo.crm.utils.BaseMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
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
	List<Product> selectSameNameProduct(@Param("name") String name, @Param("id") Long id);
	List<Product> selectByName(@Param("name") String name);
	int selectCountByCategoryId(@Param("categoryId") Long id);
	Product selectById(@Param("id") Long id);
	PageList<Product> selectByCategoryId(@Param("categoryId") Long categoryId);
}
