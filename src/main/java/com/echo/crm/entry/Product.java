package com.echo.crm.entry;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 14:56
 */

@Data
public class Product extends BaseEntry {
    @NotNull(message = "产品名称不能为空")
    private String name;
    @NotNull(message = "产品状态不能为空")
    private Integer disabled;
}
