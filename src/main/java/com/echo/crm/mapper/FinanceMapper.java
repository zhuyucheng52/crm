package com.echo.crm.mapper;

import com.echo.crm.entry.Finance;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Mapper
public interface FinanceMapper extends BaseMapper<Finance> {
    PageList<Finance> selectByType(Integer type, PageBounds pageBounds);
}
