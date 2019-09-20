package com.echo.crm.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @author yucheng
 * @description
 * @create 2019-09-20 23:03
 */

public interface BaseService<T> {
    T findById(Long id);
    T add(T t);
    T update(T t);
    T delete(Long id);
    PageList<T> findByKeyword(String key, PageBounds pageBounds);
}
