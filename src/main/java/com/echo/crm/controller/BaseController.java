package com.echo.crm.controller;

import com.echo.crm.utils.Page;
import com.echo.crm.utils.ResultInfo;

/**
 * @author yucheng
 * @description
 * @create 2019-09-20 22:23
 */

public interface BaseController<T> {
    ResultInfo<T> findById(Long id);
    ResultInfo<Page<T>> findByKeyword(Integer page, Integer limit, String key);
    ResultInfo<T> add(T t);
    ResultInfo<T> update(T t);
    ResultInfo<T> delete(Long id);
}
