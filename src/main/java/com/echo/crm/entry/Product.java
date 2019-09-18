package com.echo.crm.entry;

import lombok.Data;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 14:56
 */

@Data
public class Product extends BaseEntry {
    private Long id;
    private String name;
    private Integer disabled;
    private String remark;
    private Long userId;
}
