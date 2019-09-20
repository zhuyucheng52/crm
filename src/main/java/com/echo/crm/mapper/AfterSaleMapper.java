package com.echo.crm.mapper;

import com.echo.crm.entry.AfterSale;
import com.echo.crm.utils.BaseMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

public interface AfterSaleMapper extends BaseMapper<AfterSale> {
    PageList<AfterSale> findAfterSales(@Param("key") String key, @Param("pageBounds") PageBounds pageBounds);
}
