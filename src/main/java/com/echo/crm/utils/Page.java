package com.echo.crm.utils;

import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import lombok.Data;

import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 20:05
 */

@Data
public class Page<T> {
    private List<T> list;
    private Paginator paginator;

    public Page(List<T> list, Paginator paginator) {
        this.list = list;
        this.paginator = paginator;
    }
}
