package com.echo.crm.utils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 16:46
 */

public class PageUtils {
    public static PageBounds createPageBounds(Integer page, Integer limit) {
        if (page == null && limit == null) {
            return new PageBounds();
        } else if (page != null && limit == null) {
            return new PageBounds(page, Consts.PAGE_SIZE);
        } else if (page == null && limit != null) {
            return new PageBounds(limit);
        } else {
            return new PageBounds(page, limit);
        }
    }

    public static <T> Page<T> createPage(PageList<T> list) {
        return new Page(list, list.getPaginator());
    }
}
